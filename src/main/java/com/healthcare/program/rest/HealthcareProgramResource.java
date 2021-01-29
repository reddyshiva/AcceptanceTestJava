package com.healthcare.program.rest;

import javax.websocket.server.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

import com.healthcare.schema.enrollees.pojo.DependentsRequestResponce;
import com.healthcare.schema.enrollees.pojo.UserRequest;


public interface HealthcareProgramResource {
		
	 

	public Response addEnrollees(final UserRequest user , HttpHeaders httpHeaders) throws Exception;
	
	public Response getEnrollees(String userId , HttpHeaders httpHeaders) throws Exception;
	
	public Response updateEnrollees(final UserRequest  user ,@Context HttpHeaders httpHeaders) throws Exception;
	

	public Response deleteEnrollees(@PathParam("userId") String userId,@Context HttpHeaders httpHeaders) throws Exception;
	

	public Response addDependant(final DependentsRequestResponce user ,@Context HttpHeaders httpHeaders , String userId) throws Exception;

	public Response updateDependant(final DependentsRequestResponce user ,@Context HttpHeaders httpHeaders ,String userId) throws Exception;
	

	public Response deleteDependant(@Context HttpHeaders httpHeaders ,@PathParam("userId") String userId) throws Exception;

}
