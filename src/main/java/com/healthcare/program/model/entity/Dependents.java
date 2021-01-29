package com.healthcare.program.model.entity;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

;
@Entity
@Table(name = "Dependents")
public class Dependents implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Id
    @Column(name = "DEPENDENT_USER_ID")
    private String  dependentUserId;
    
    
    @Column(name = "USER_ID")
    private String userId;
    
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;
    
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private java.util.Date birthDate;
    

    

	@ManyToOne
	@JoinColumn(name="USER_ID",insertable = false, updatable = false)
	private Users users;


	
	
	public String getDependentUserId() {
		return dependentUserId;
	}

	public void setDependentUserId(String dependentUserId) {
		this.dependentUserId = dependentUserId;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public java.util.Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(java.util.Date birthDate) {
		this.birthDate = birthDate;
	}
}