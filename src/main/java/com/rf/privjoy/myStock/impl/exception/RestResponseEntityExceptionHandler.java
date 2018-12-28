package com.rf.privjoy.myStock.impl.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.rf.privjoy.myStock.impl.dao.hibernate.HibernateUtil;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	private String CONSTRAINT_VIOLATION_MESSAGE = "SQL Integrity Constraints violated. Please double check the request body";

	@ExceptionHandler(value = ResponseStatusException.class)
	public ResponseEntity<Object> handleResponseStatusException(ResponseStatusException ex, WebRequest request) {
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), ex.getStatus(), request);
	}
	
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request) {
		HibernateUtil.getSessionFactory().getCurrentSession().close();;
		return handleExceptionInternal(ex, CONSTRAINT_VIOLATION_MESSAGE, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}
}
