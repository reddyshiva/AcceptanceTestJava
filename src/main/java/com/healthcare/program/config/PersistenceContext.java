package com.healthcare.program.config;

import java.util.Collections;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.validation.Valid;

import org.apache.tomcat.jdbc.pool.PoolConfiguration;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * Persistence Context for DB operations
 * 
 * @author reddyshiva
 *
 */
@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.healthcare.program.model" })
@EnableJpaRepositories(basePackages = "com.healthcare.program.model.repository",
entityManagerFactoryRef = "healthcareEntityManagerFactory",
transactionManagerRef ="healthcareTransactionManager")
public class PersistenceContext {


	@Autowired
	private ConfigurableEnvironment env;

	@Bean
	@ConfigurationProperties(prefix = "healthcare.db")
	@Valid
	public PoolConfiguration getPoolProperties() {
		return new PoolProperties();
	}

	

	@Bean
	@ConfigurationProperties(prefix = "db.healthcare")
	public DataSource dataSource() {
		PoolConfiguration poolProperties = getPoolProperties();
		org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource(poolProperties);
		return dataSource;
	}

	
	@Bean(name="healthcareTransactionManager")
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
		return new JpaTransactionManager(emf);
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
		bean.setDatabase(Database.H2);
		bean.setGenerateDdl(true);
		//bean.setShowSql(true);
		return bean;
	}
	
    @Bean(name="healthcareEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean subscriptionEntityManagerFactory() throws ClassNotFoundException {
        
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        emf.setPersistenceUnitName("healthcarePersistence");
        emf.setPackagesToScan("com.healthcare.program.model.entity");
        emf.setJpaVendorAdapter(jpaVendorAdapter());
        emf.setJpaPropertyMap(Collections.singletonMap("javax.persistence.validation.mode", "none"));

        return emf;
    }
	

}
