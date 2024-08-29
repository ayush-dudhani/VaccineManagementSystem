package com.wu.vaccine.VaccineManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RESTExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(CitizenNotFoundException cnfe)
	{
		ErrorResponse myResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), cnfe.getMessage(),System.currentTimeMillis());
		return new ResponseEntity(myResponse,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleException(Exception e)
	{
		ErrorResponse myResponse=new ErrorResponse(HttpStatus.BAD_REQUEST.value(), e.getMessage(),System.currentTimeMillis());
		return new ResponseEntity(myResponse,HttpStatus.BAD_REQUEST); 
	}
}
