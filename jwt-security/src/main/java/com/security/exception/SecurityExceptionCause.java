package com.security.exception;

import org.springframework.http.HttpStatus;

import com.app.base.exception.ApplicationExceptionCause;

public enum SecurityExceptionCause implements ApplicationExceptionCause {
	NOT_FOUND("User not found", HttpStatus.NOT_FOUND),
	ALREADY_EXISTS("User already exists", HttpStatus.CONFLICT);
	
	private String code;
	private HttpStatus httpStatus;
	
	SecurityExceptionCause(String code, HttpStatus httpStatus) {
		this.code = code;
		this.httpStatus = httpStatus;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public HttpStatus getHttpStatus() {
		return this.httpStatus;
	}

}
 