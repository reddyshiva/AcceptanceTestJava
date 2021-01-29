package com.healthcare.program.logging;
public class HealthCareContext {

    protected String healthCareTid;
    protected String userId;
    protected String appName;
    protected String locale;
    protected String dependentUserId;
    
    
	public String getDependentUserId() {
		return dependentUserId;
	}
	public void setDependentUserId(String dependentUserId) {
		this.dependentUserId = dependentUserId;
	}
	public String getHealthCareTid() {
		return healthCareTid;
	}
	public void setHealthCareTid(String healthCareTid) {
		this.healthCareTid = healthCareTid;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
    
    
   
}