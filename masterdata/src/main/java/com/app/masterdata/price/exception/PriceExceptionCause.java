package com.app.masterdata.price.exception;

import org.springframework.http.HttpStatus;

import com.app.base.exception.ApplicationExceptionCause;

public enum PriceExceptionCause implements ApplicationExceptionCause {
	NO_PRICES_SET("No prices have been set for this product", HttpStatus.CONFLICT);
	
	private String code;
	private HttpStatus httpStatus;
	
	PriceExceptionCause(String code, HttpStatus httpStatus) {
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
