package com.healthcare.program.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.program.model.entity.Users;

public interface HealthCareUserRepository extends JpaRepository<Users, Integer>{
	

	
	public Users findByUserId(String userId);
	
	@Modifying
	@Query("update Users usr set usr.activationStatus = :activationStatus ,usr.mobileNumber = :mobileNumber,usr.userName = :userName   where usr.userId = :userId")
	public int updateUser(@Param("activationStatus") String activationStatus, @Param("mobileNumber") String mobileNumber,@Param("userName") String userName, @Param("userId") String userId);
	
	
}