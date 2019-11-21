package com.app.user_profile.exception;

import org.springframework.http.HttpStatus;

import com.app.base.exception.ApplicationExceptionCause;

public enum ProfileExceptionCause implements ApplicationExceptionCause {
	NOT_FOUND("Profile not found", HttpStatus.NOT_FOUND);

	private String code;
	private HttpStatus httpStatus;
	
	ProfileExceptionCause(String code, HttpStatus httpStatus) {
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
