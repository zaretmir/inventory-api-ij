package com.app.hangars.exception;

import org.springframework.http.HttpStatus;

import com.app.base.exception.ApplicationExceptionCause;

public enum HangarExceptionCause implements ApplicationExceptionCause {
	NOT_FOUND("No hangars found", HttpStatus.NOT_FOUND),
	NOT_FOUND_ACTIVE("No active hangars found", HttpStatus.NOT_FOUND),
	NAME_DUPLICATED("An hangar with this name already exists", HttpStatus.CONFLICT);;
	
	private String code;
	private HttpStatus httpStatus;
	
	HangarExceptionCause(String code, HttpStatus httpStatus) {
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
