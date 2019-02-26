package com.qs.contact.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import com.qs.contact.dto.ErrorDto;
import com.qs.contact.exception.ValidationException;

@ControllerAdvice
public class ErrorController {
	
	@ExceptionHandler(ValidationException.class)
	public HttpEntity<ErrorDto> handleValidation(ValidationException e) {
		
		ErrorDto error =new ErrorDto();
		error.setInternalCode("4020");
		error.setMessage(e.getMessage());
		
		return new ResponseEntity<ErrorDto>(error, e.getStatus());
	} 
}
