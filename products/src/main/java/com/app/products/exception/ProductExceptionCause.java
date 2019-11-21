package com.app.products.exception;

import org.springframework.http.HttpStatus;

import com.app.base.exception.ApplicationExceptionCause;

public enum ProductExceptionCause implements ApplicationExceptionCause {
	
	NOT_FOUND("No products found", HttpStatus.NOT_FOUND),
	NOT_FOUND_ACTIVE("No active productsfound", HttpStatus.NOT_FOUND),
	NAME_DUPLICATED("A product with this name already exists", HttpStatus.CONFLICT);
	
	private String code;
	private HttpStatus httpStatus;
	
	ProductExceptionCause(String code, HttpStatus httpStatus) {
		this.code = code;
		this.httpStatus = httpStatus;
	}
	
	@Override
	public String getCode() {
		return this.code;
	}
	
	@Override
	public HttpStatus  getHttpStatus() {
		return this.httpStatus;
	}

}
