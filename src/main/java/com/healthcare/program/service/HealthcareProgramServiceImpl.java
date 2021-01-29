
package com.healthcare.program.service;

import javax.ws.rs.BadRequestException;
import javax.ws.rs.core.HttpHeaders;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.healthcare.program.exception.InternalServerException;
import com.healthcare.program.exception.NotFoundException;
import com.healthcare.program.exception.ServiceUnavailableException;
import com.healthcare.program.mapper.UsersRequestResponceMapper;
import com.healthcare.program.model.entity.Dependents;
import com.healthcare.program.model.entity.Users;
import com.healthcare.program.model.repository.HealthCareDependentsRepository;
import com.healthcare.program.model.repository.HealthCareUserRepository;
import com.healthcare.schema.enrollees.pojo.DependentsRequestResponce;
import com.healthcare.schema.enrollees.pojo.UserRequest;
import com.healthcare.schema.enrollees.pojo.UsersResponce;

@Service
public class HealthcareProgramServiceImpl implements HealthcareProgramService {

	private static final Logger LOGGER = LoggerFactory.getLogger(HealthcareProgramServiceImpl.class);

	@Autowired
	private HealthCareUserRepository healthCareUserRepository;

	@Autowired
	private HealthCareDependentsRepository healthCareDependentsRepository;

	@Override
	@Retryable(label = "HealthCare", exclude = { BadRequestException.class, NotFoundException.class }, include = {
			Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 2, maxDelay = 20000))
	@Transactional(transactionManager = "healthcareTransactionManager")
	public UsersResponce addEnrollees(UserRequest user, HttpHeaders httpHeaders) throws Exception {

		try {
			LOGGER.info("Processing addEnrollees Request ={}", user);
			Users Users = healthCareUserRepository.saveAndFlush(UsersRequestResponceMapper.mapRequestToEntity(user));
			return UsersRequestResponceMapper.mapEntityTOResponce(Users);
		} catch (Exception e) {

			LOGGER.error("unabel to persist user data ,exception = {}", e);

			if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {

				throw new com.healthcare.program.exception.BadRequestException("user already exists");
			} else {
				throw new InternalServerException(e.getMessage());
			}
		}

	}

	@Retryable(label = "HealthCare", exclude = { BadRequestException.class, NotFoundException.class }, include = {
			Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 2, maxDelay = 20000))

	@Override
	public UsersResponce getEnrollees(String userId, HttpHeaders httpHeaders) throws Exception {

		LOGGER.info("Processing getEnrollees Request ={}", userId);
		Users users = healthCareUserRepository.findByUserId(userId);
		if (users == null) {
			throw new NotFoundException("user not found");
		}
		return UsersRequestResponceMapper.mapEntityTOResponce(users);
	}

	@Retryable(label = "HealthCare", exclude = { BadRequestException.class, NotFoundException.class }, include = {
			Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 2, maxDelay = 20000))

	@Override
	@Transactional
	public void updateEnrollees(UserRequest userRequest, HttpHeaders httpHeaders) throws Exception {
		try {
			LOGGER.info("Processing updateEnrollees Request ={}", userRequest);
			Users users = healthCareUserRepository.findByUserId(userRequest.getUserId());
			if (users == null) {
				throw new NotFoundException("user not found");
			}
			healthCareUserRepository.updateUser(userRequest.getActivationStatus(), userRequest.getMobileNumber(),
					userRequest.getMobileNumber(), userRequest.getUserId());
		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	@Retryable(label = "HealthCare", exclude = { BadRequestException.class, NotFoundException.class }, include = {
			Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 2, maxDelay = 20000))

	@Override
	public void deleteEnrollees(String userId, HttpHeaders httpHeaders) throws Exception {
		LOGGER.info("Processing deleteEnrollees Request ={}", userId);
		Users users = healthCareUserRepository.findByUserId(userId);
		if (users == null) {
			throw new NotFoundException("user not found");
		}
		try {
			healthCareUserRepository.delete(users);
		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	@Retryable(label = "HealthCare", exclude = { BadRequestException.class, NotFoundException.class }, include = {
			Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 2, maxDelay = 20000))
	@Override
	public DependentsRequestResponce addDependant(DependentsRequestResponce dependentsRequest, HttpHeaders httpHeaders,
			String userId) throws Exception {
		LOGGER.info("Processing addDependant Request ={} ,userId={}", dependentsRequest, userId);
		try {
			Users users = healthCareUserRepository.findByUserId(userId);
			if (users == null) {
				throw new NotFoundException("user not found");
			}
			LOGGER.info("mapping dependentsRequest to Entity, dependentsRequest={}", dependentsRequest);
			Dependents request = UsersRequestResponceMapper.mapRequestToEntity(dependentsRequest, userId);
			LOGGER.info("Entity request ={}", request.toString());

			Dependents dependentsResponce = healthCareDependentsRepository.saveAndFlush(request);
			return UsersRequestResponceMapper.mapEntityTOResponce(dependentsResponce);
		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}

	}

	@Retryable(label = "HealthCare", exclude = { BadRequestException.class, NotFoundException.class }, include = {
			Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 2, maxDelay = 20000))
	@Override
	@Transactional
	public void updateDependant(DependentsRequestResponce dependentsRequest, HttpHeaders httpHeaders, String userId)
			throws Exception {
		LOGGER.info("Processing updateDependant Request ={} ,userId={}", dependentsRequest, userId);
		try {
			Dependents dependents = healthCareDependentsRepository.findByDependentUserId(dependentsRequest.getUserId());
			if (dependents == null) {
				throw new NotFoundException("user not found");
			}
			healthCareDependentsRepository.updateDependents(dependentsRequest.getMobileNumber(),
					dependentsRequest.getUserName(), userId);
		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	@Retryable(label = "HealthCare", exclude = { BadRequestException.class, NotFoundException.class }, include = {
			Exception.class }, maxAttempts = 3, backoff = @Backoff(delay = 2000, multiplier = 2, maxDelay = 20000))
	@Override
	@Transactional
	public void deleteDependant(HttpHeaders httpHeaders, String userId) throws Exception {
		LOGGER.info("Processing deleteDependant Request ={}", userId);

		Dependents dependents = healthCareDependentsRepository.findByDependentUserId(userId);
		if (dependents == null) {
			throw new NotFoundException("user not found");
		}
		try {
			healthCareDependentsRepository.delteDependents(userId);
		} catch (Exception e) {
			throw new InternalServerException(e.getMessage());
		}
	}

	@Recover
	public void recover(final Exception e, UserRequest user, HttpHeaders httpHeaders) throws Throwable {
		throw new ServiceUnavailableException();
	}

	@Recover
	public void recover(final Exception e, HttpHeaders httpHeaders, String userId) throws Throwable {
		throw new ServiceUnavailableException();
	}
	
	@Recover
	public void recover(final Exception e, DependentsRequestResponce dependentsRequest, HttpHeaders httpHeaders, String userId) throws Throwable {
		throw new ServiceUnavailableException();
	}
	
	@Recover
	public void recover(final Exception e,String userId, HttpHeaders httpHeaders) throws Throwable {
		throw new ServiceUnavailableException();
	}


}
