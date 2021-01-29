package com.healthcare.program.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.program.model.entity.Dependents;

public interface HealthCareDependentsRepository extends JpaRepository<Dependents, Integer>{
	
	public Dependents findByDependentUserId(String dependentUserId);
	
	@Modifying
	@Query("update Dependents dep set dep.mobileNumber = :mobileNumber,dep.userName = :userName   where dep.dependentUserId = :userId")
	public int updateDependents(@Param("mobileNumber") String mobileNumber,@Param("userName") String userName, @Param("userId") String userId);
	
	@Modifying
	@Query("delete  from Dependents dep  where dep.dependentUserId = :userId")	
	public int delteDependents( @Param("userId") String userId);
	
}
