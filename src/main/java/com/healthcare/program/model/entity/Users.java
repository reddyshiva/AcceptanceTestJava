package com.healthcare.program.model.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

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

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

;
@Entity
@Table(name = "users")
public class Users implements Serializable {
	
	private static final long serialVersionUID = 1L;

	


	
	@Id
    @Column(name = "USER_ID")
    private String userId;
	
    
    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;
    
    @Column(name = "BIRTH_DATE")
    @Temporal(TemporalType.DATE)
    private java.util.Date birthDate;
    
    @Column(name = "ACTIVATION_STATUS")
    private String activationStatus;
 
    @Column(name = "EMAIL_ID")
    private String emailID;
    
   
    
	@OneToMany(mappedBy="users",fetch = FetchType.EAGER,cascade=CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<Dependents> dependents;

	
	
	
	public List<Dependents> getDependents() {
		return dependents;
	}

	public void setDependents(List<Dependents> dependents) {
		this.dependents = dependents;
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

	public String getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(String activationStatus) {
		this.activationStatus = activationStatus;
	}

	public String getEmailID() {
		return emailID;
	}

	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}


    
}