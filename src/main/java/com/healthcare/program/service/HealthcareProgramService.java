
package com.healthcare.program.service ;

import javax.ws.rs.core.HttpHeaders;

import com.healthcare.schema.enrollees.pojo.DependentsRequestResponce;
import com.healthcare.schema.enrollees.pojo.UserRequest;
import com.healthcare.schema.enrollees.pojo.UsersResponce;

public interface HealthcareProgramService {
	
	
	public UsersResponce addEnrollees(final UserRequest user , HttpHeaders httpHeaders) throws Exception;
	
	public UsersResponce getEnrollees(String userId , HttpHeaders httpHeaders) throws Exception;
	
	public void updateEnrollees(final UserRequest user ,HttpHeaders httpHeaders) throws Exception;
	
	public void deleteEnrollees(String UsersRequestResponce,HttpHeaders httpHeaders) throws Exception;
	

	public DependentsRequestResponce addDependant(final DependentsRequestResponce user ,HttpHeaders httpHeaders , String userId) throws Exception;

	public void updateDependant(final DependentsRequestResponce user ,HttpHeaders httpHeaders ,String userId) throws Exception;
	

	public void deleteDependant(HttpHeaders httpHeaders ,String userId) throws Exception;
	

}
