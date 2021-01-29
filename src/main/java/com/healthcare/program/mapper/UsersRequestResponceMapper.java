package com.healthcare.program.mapper;

import javax.xml.datatype.DatatypeConfigurationException;

import com.google.common.base.Strings;
import com.healthcare.program.exception.BadRequestException;
import com.healthcare.program.model.entity.Dependents;
import com.healthcare.program.model.entity.Users;
import com.healthcare.program.util.DateFormatter;
import com.healthcare.schema.enrollees.pojo.DependentsRequestResponce;
import com.healthcare.schema.enrollees.pojo.UserRequest;
import com.healthcare.schema.enrollees.pojo.UsersResponce;


public class UsersRequestResponceMapper {

	public static Users mapRequestToEntity(UserRequest usersRequest) {

		if (Strings.isNullOrEmpty(usersRequest.getUserName()) || Strings.isNullOrEmpty(usersRequest.getUserId()) || usersRequest.getBirthDate()==null) {
			throw new BadRequestException("required filed is misisng  UserName/BirthDate/UserId");
		}
		Users users = new Users();
		if (!Strings.isNullOrEmpty(usersRequest.getUserId())) {
			users.setUserId(usersRequest.getUserId());
			users.setUserName(usersRequest.getUserName());
			users.setBirthDate(usersRequest.getBirthDate().toGregorianCalendar().getTime());
			users.setMobileNumber(usersRequest.getMobileNumber());
		    users.setActivationStatus(usersRequest.getActivationStatus());
			users.setEmailID(usersRequest.getEmailID());
		}
		return users;

	}

	public static Dependents mapRequestToEntity(DependentsRequestResponce dependentsRequest,String userUserId) {
		
		if (Strings.isNullOrEmpty(dependentsRequest.getUserName()) || Strings.isNullOrEmpty(dependentsRequest.getUserId()) || dependentsRequest.getBirthDate()==null) {
			throw new BadRequestException("required filed is misisng  UserName/BirthDate/UserId");
		}
		Dependents dependent = new Dependents();
		dependent.setUserId(userUserId);
		dependent.setUserName(dependentsRequest.getUserName());
		dependent.setBirthDate(dependentsRequest.getBirthDate().toGregorianCalendar().getTime());
		dependent.setMobileNumber(dependentsRequest.getMobileNumber());
		dependent.setDependentUserId(dependentsRequest.getUserId());
		return dependent;
	}
	
	public static DependentsRequestResponce mapEntityTOResponce(Dependents dependents) throws DatatypeConfigurationException {

		DependentsRequestResponce dependentsRequestResponce=null;
		if (dependents != null) {
				dependentsRequestResponce = new DependentsRequestResponce();
				dependentsRequestResponce.setUserId(dependents.getDependentUserId());
				dependentsRequestResponce.setUserName(dependents.getUserName());
				dependentsRequestResponce.setBirthDate(DateFormatter.convertDateToXMLGregorianCalendar(dependents.getBirthDate()));
				dependentsRequestResponce.setMobileNumber(dependents.getMobileNumber());
			}
		return dependentsRequestResponce;

	}

	public static UsersResponce mapEntityTOResponce(Users users) throws DatatypeConfigurationException {

		UsersResponce userResponce = new UsersResponce();


		userResponce.setUserName(users.getUserName());
		userResponce.setUserId(users.getUserId());
		userResponce.setBirthDate(DateFormatter.convertDateToXMLGregorianCalendar(users.getBirthDate()));
		userResponce.setMobileNumber(users.getMobileNumber());
		userResponce.setActivationStatus(users.getActivationStatus());
		userResponce.setEmailID(users.getEmailID());

		if (users.getDependents() != null) {
			for (Dependents dependent : users.getDependents()) {
				DependentsRequestResponce dependentsRequestResponce = new DependentsRequestResponce();

				dependentsRequestResponce.setUserId(dependent.getDependentUserId());
				dependentsRequestResponce.setUserName(dependent.getUserName());
				dependentsRequestResponce
						.setBirthDate(DateFormatter.convertDateToXMLGregorianCalendar(dependent.getBirthDate()));
				dependentsRequestResponce.setMobileNumber(dependent.getMobileNumber());
				userResponce.getDependents().add(dependentsRequestResponce);
			}
		}

		return userResponce;

	}

}
