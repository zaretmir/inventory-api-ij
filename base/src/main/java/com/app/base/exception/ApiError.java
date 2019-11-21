package com.app.base.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

public class ApiError {
	
	private HttpStatus status;

	private LocalDateTime timestamp;
	private String message;	// User-friendly error message
	private String debugMessage; // Detailed error message
	private List<ApiSubError> subErrors;
	
	public ApiError() {
		this.timestamp = LocalDateTime.now();
	}
	
	
	ApiError(HttpStatus status) {
		this();
		this.status = status;
	}
	
	ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.debugMessage = ex.getLocalizedMessage();
	}
	
	ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.debugMessage = ex.getLocalizedMessage();
	}
	
	private void addSubError(ApiSubError subError) {
		if (subErrors == null) {
			subErrors = new ArrayList<ApiSubError>();
		}		
		subErrors.add(subError);
	}
	
	
	public void addValidationErrors(List<FieldError> fieldErrors) {
		fieldErrors.forEach(this::addValidationError);
		
	}
	
	private void addValidationError(FieldError fieldError) {
		ApiValidationError validationError = new ApiValidationError(
				fieldError.getObjectName(),
				fieldError.getField(),
				fieldError.getRejectedValue(),
				fieldError.getDefaultMessage());
		addSubError(validationError);
	}
	
	


	public HttpStatus getStatus() {
		return status;
	}


	public void setStatus(HttpStatus status) {
		this.status = status;
	}


	public LocalDateTime getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getDebugMessage() {
		return debugMessage;
	}


	public void setDebugMessage(String debugMessage) {
		this.debugMessage = debugMessage;
	}


	public List<ApiSubError> getSubErrors() {
		return subErrors;
	}


	public void setSubErrors(List<ApiSubError> subErrors) {
		this.subErrors = subErrors;
	}

}
