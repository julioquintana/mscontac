package com.qs.contact.exception;

import org.springframework.http.HttpStatus;

public class ValidationException extends Exception {

	private HttpStatus status;
	private String codError;
	
	public ValidationException(String message, String codError){
		super(message);
		this.codError = codError;
	}
	
	public ValidationException(String codError, String message, HttpStatus status){
		super(message);
		this.status = status;
		this.codError = codError;
	}
	
	public HttpStatus getStatus() {
		return status;
	}
	
}
