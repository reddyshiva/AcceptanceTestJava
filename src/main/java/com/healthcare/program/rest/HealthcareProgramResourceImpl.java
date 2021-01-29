package com.healthcare.program.rest;

import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.healthcare.program.constants.Constants;
import com.healthcare.program.logging.ContextThreadLocal;
import com.healthcare.program.service.HealthcareProgramService;
import com.healthcare.schema.enrollees.pojo.DependentsRequestResponce;
import com.healthcare.schema.enrollees.pojo.UserRequest;
import com.healthcare.schema.enrollees.pojo.UsersResponce;

@Component
@Path("/enrollees")
public class HealthcareProgramResourceImpl implements HealthcareProgramResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(HealthcareProgramResourceImpl.class);

	@Autowired
	private HealthcareProgramService healthcareProgramService;
	
	

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("create")
	@Override
	public Response addEnrollees(final UserRequest user, @Context HttpHeaders httpHeaders) throws Exception {


		
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_TID,UUID.randomUUID().toString());
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_USERID,user.getUserId());
		LOGGER.info("request Recived for  create Enrollees ={}", user);
		UsersResponce usersResponce = null;

		try {
			usersResponce = healthcareProgramService.addEnrollees(user, httpHeaders);
		} catch (com.healthcare.program.exception.BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok(usersResponce).build();

	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("update")
	@Override
	public Response updateEnrollees(final UserRequest user, @Context HttpHeaders httpHeaders) throws Exception {

		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_TID,UUID.randomUUID().toString());
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_USERID,user.getUserId());
		LOGGER.info(" request Recived for  update Enrollees ={}", user);
		try {
			healthcareProgramService.updateEnrollees(user, httpHeaders);
		} catch (com.healthcare.program.exception.BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		return Response.ok().build();


	}

	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{userId}")
	@Override
	public Response deleteEnrollees(@PathParam("userId") String userId, @Context HttpHeaders httpHeaders)
			throws Exception {
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_TID,UUID.randomUUID().toString());
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_USERID,userId);
		LOGGER.info(" request Recived for  delete  Enrollees ={}", userId);	
		
		try {
			healthcareProgramService.deleteEnrollees(userId, httpHeaders);
		} catch (com.healthcare.program.exception.BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		catch (com.healthcare.program.exception.InternalServerException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		catch (com.healthcare.program.exception.ServiceUnavailableException e) {
			return Response.status(Response.Status.GATEWAY_TIMEOUT).entity(e.getMessage()).build();
		}
		catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		
		
		
		return Response.ok().build();

	}

	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{userId}/createDependant")
	@Override
	public Response addDependant(final DependentsRequestResponce user, @Context HttpHeaders httpHeaders,
			@PathParam("userId") String userId) throws Exception {
		
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_TID,UUID.randomUUID().toString());
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_USERID,userId);
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_DEPENDENTUSERID,user.getUserId());
		LOGGER.info(" request Recived for  addDependant ={}", user);	
		DependentsRequestResponce responce = null;
		try {
			responce = healthcareProgramService.addDependant(user, httpHeaders, userId);
		} catch (com.healthcare.program.exception.BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		catch (com.healthcare.program.exception.InternalServerException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		catch (com.healthcare.program.exception.ServiceUnavailableException e) {
			return Response.status(Response.Status.GATEWAY_TIMEOUT).entity(e.getMessage()).build();
		}
		catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return Response.ok(responce).build();

	}

	@PUT
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{userId}/updateDependant")
	@Override
	public Response updateDependant(final DependentsRequestResponce user, @Context HttpHeaders httpHeaders,
			@PathParam("userId") String userId) throws Exception {

		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_TID,UUID.randomUUID().toString());
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_USERID,userId);
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_DEPENDENTUSERID,user.getUserId());
		LOGGER.info(" request Recived for  updateDependant ={}", user);	
		
		try {
			healthcareProgramService.updateDependant(user, httpHeaders, userId);
		} catch (com.healthcare.program.exception.BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		catch (com.healthcare.program.exception.InternalServerException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		catch (com.healthcare.program.exception.ServiceUnavailableException e) {
			return Response.status(Response.Status.GATEWAY_TIMEOUT).entity(e.getMessage()).build();
		}
		catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return Response.ok().build();
	}

	@DELETE
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{userId}/deleteDependant")
	@Override
	public Response deleteDependant(@Context HttpHeaders httpHeaders, @PathParam("userId") String userId)
			throws Exception {

		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_TID,UUID.randomUUID().toString());
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_DEPENDENTUSERID,userId);
		LOGGER.info(" request Recived for  deleteDependant ={}", userId);	
		try {
			healthcareProgramService.deleteDependant(httpHeaders, userId);
		} catch (com.healthcare.program.exception.BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		catch (com.healthcare.program.exception.InternalServerException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		catch (com.healthcare.program.exception.ServiceUnavailableException e) {
			return Response.status(Response.Status.GATEWAY_TIMEOUT).entity(e.getMessage()).build();
		}
		catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		return Response.ok().build();

	}

	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{userId}")
	@Override
	public Response getEnrollees(@PathParam("userId") String userId, @Context HttpHeaders httpHeaders)
			throws Exception {
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_TID,UUID.randomUUID().toString());
		ContextThreadLocal.addToMDC(Constants.HEALTHCARE_USERID,userId);
		LOGGER.info(" request Recived for  getEnrollees ={}", userId);	
		UsersResponce users = null;
		try {
			users = healthcareProgramService.getEnrollees(userId, httpHeaders);
		} catch (com.healthcare.program.exception.BadRequestException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
		catch (com.healthcare.program.exception.InternalServerException e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}
		catch (com.healthcare.program.exception.ServiceUnavailableException e) {
			return Response.status(Response.Status.GATEWAY_TIMEOUT).entity(e.getMessage()).build();
		}
		catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
		}

		return Response.ok(users).build();
	}

}
