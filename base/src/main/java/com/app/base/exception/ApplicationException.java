package com.app.base.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class ApplicationException extends RuntimeException {

	private static final long serialVersionUID = 2435263913277497590L;
	
	private HttpStatus httpStatus;
	private String message;	// User-friendly error message
	
	public ApplicationException(ApplicationExceptionCause cause) {
		this.httpStatus = cause.getHttpStatus();
		this.message = cause.getCode();
	}
}
