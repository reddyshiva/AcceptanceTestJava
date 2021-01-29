package com.healthcare.program;

import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.healthcare.program.service.HealthcareProgramService;
import com.healthcare.program.util.DateFormatter;
import com.healthcare.schema.enrollees.pojo.DependentsRequestResponce;
import com.healthcare.schema.enrollees.pojo.UserRequest;
import com.healthcare.schema.enrollees.pojo.UsersResponce;

@SpringBootTest
class HealthcareProgramApplicationTests {
	
	@Autowired
	private HealthcareProgramService healthcareProgramService;

	@Test
	@Order(1)
	void contextLoads() {
	}
	

	@Test
	public void addEnrollees() throws Exception{
		
		UserRequest user=new UserRequest();
		user.setUserId("3213123");
		user.setUserName("test user");
		user.setMobileNumber("24323143241");
		user.setBirthDate(DateFormatter.convertDateToXMLGregorianCalendar(new Date()));
		user.setActivationStatus("true");
		UsersResponce usersResponce=healthcareProgramService.addEnrollees(user, null);
		Assert.assertNotNull("The usersResponce cannot be null.",usersResponce);
		Assert.assertEquals(user.getUserId(), usersResponce.getUserId());
		Assert.assertEquals(user.getUserName(), usersResponce.getUserName());
		Assert.assertEquals(user.getActivationStatus(), usersResponce.getActivationStatus());
		Assert.assertEquals(user.getMobileNumber(), usersResponce.getMobileNumber());
	}
	
	@Test
	public void updateEnrollees() throws Exception{		
		UserRequest user=new UserRequest();
		user.setUserId("12346");
		user.setUserName("test user");
		user.setMobileNumber("2432314324424");
		user.setBirthDate(DateFormatter.convertDateToXMLGregorianCalendar(new Date()));
		user.setActivationStatus("true");
		healthcareProgramService.updateEnrollees(user, null);
		UsersResponce updateEnrollees = healthcareProgramService.addEnrollees(user, null);
		Assert.assertNotNull("The usersResponce cannot be null.",updateEnrollees);
		Assert.assertEquals(user.getUserId(), updateEnrollees.getUserId());
		Assert.assertEquals(user.getUserName(), updateEnrollees.getUserName());
		Assert.assertEquals(user.getActivationStatus(), updateEnrollees.getActivationStatus());
		Assert.assertEquals(user.getMobileNumber(), updateEnrollees.getMobileNumber());
	}

	
	@Test
	public void addDependant() throws Exception{		
		DependentsRequestResponce user=new DependentsRequestResponce();
		user.setUserId("2313123213");
		user.setUserName("test user");
		user.setMobileNumber("2432314324424");
		user.setBirthDate(DateFormatter.convertDateToXMLGregorianCalendar(new Date()));
		DependentsRequestResponce addDependant = healthcareProgramService.addDependant(user, null, "12346");
		Assert.assertNotNull("The usersResponce cannot be null.",addDependant);
		Assert.assertEquals(user.getUserId(), addDependant.getUserId());
		Assert.assertEquals(user.getUserName(), addDependant.getUserName());
		Assert.assertEquals(user.getMobileNumber(), addDependant.getMobileNumber());
		
		user.setUserId("2313123213");
		DependentsRequestResponce addDependant1 = healthcareProgramService.addDependant(user, null, "12346");
		Assert.assertNotNull("The usersResponce cannot be null.",addDependant1);
		Assert.assertEquals(user.getUserId(), addDependant1.getUserId());
		Assert.assertEquals(user.getUserName(), addDependant1.getUserName());
		Assert.assertEquals(user.getMobileNumber(), addDependant1.getMobileNumber());
		
		
		user.setUserId("2313123213");
		DependentsRequestResponce addDependant2 = healthcareProgramService.addDependant(user, null, "12346");
		Assert.assertNotNull("The usersResponce cannot be null.",addDependant2);
		Assert.assertEquals(user.getUserId(), addDependant2.getUserId());
		Assert.assertEquals(user.getUserName(), addDependant2.getUserName());
		Assert.assertEquals(user.getMobileNumber(), addDependant2.getMobileNumber());
	}
	
	@Test
	public void updateDependant() throws Exception{		
		DependentsRequestResponce user=new DependentsRequestResponce();
		user.setUserId("34553425");
		user.setUserName("test user");
		user.setMobileNumber("2432314324424");
		user.setBirthDate(DateFormatter.convertDateToXMLGregorianCalendar(new Date()));
		 healthcareProgramService.updateDependant(user, null, "34553425");
		 UsersResponce usersResponce =healthcareProgramService.getEnrollees("12345", null);
		 List<DependentsRequestResponce> updateDependant=usersResponce.getDependents();
		 DependentsRequestResponce result=null;
		 for (DependentsRequestResponce dependentsRequestResponce : updateDependant) {
			
			 if("34553425".equalsIgnoreCase(dependentsRequestResponce.getUserId())){
				 result =dependentsRequestResponce;
			 }
			 
		}
		Assert.assertNotNull("The usersResponce cannot be null.",result);
		Assert.assertEquals(user.getUserId(), result.getUserId());
		Assert.assertEquals(user.getUserName(), result.getUserName());
		Assert.assertEquals(user.getMobileNumber(), result.getMobileNumber());
	}
	
	
	@Test
	public void getEnrollees() throws Exception{			
		UsersResponce usersResponce=healthcareProgramService.getEnrollees("12345", null);
		Assert.assertNotNull("The usersResponce cannot be null.",usersResponce);
		Assert.assertEquals(3, usersResponce.getDependents().size());
	}
	
	
	@Test
	public void deleteDependant() throws Exception{			
		healthcareProgramService.deleteDependant(null,"345421312");
		UsersResponce usersResponce=healthcareProgramService.getEnrollees("12346", null);
		Assert.assertNotNull("The usersResponce cannot be null.",usersResponce);
		Assert.assertEquals(2, usersResponce.getDependents().size());
	}
	
	@Test
	public void deleteEnrollees() throws Exception{			
		healthcareProgramService.deleteEnrollees("12347", null);
		try {
			healthcareProgramService.getEnrollees("12347", null);
			
		} catch (Exception e) {
			
		}
	}
}
