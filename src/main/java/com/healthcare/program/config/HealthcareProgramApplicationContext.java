package com.healthcare.program.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration

  @PropertySources({ @PropertySource(name = "local", value = {
  "classpath:application.properties" }) }) 
@ComponentScan(value = { "com.healthcare.program" })

//@Import({  })
public class HealthcareProgramApplicationContext {

	
}
