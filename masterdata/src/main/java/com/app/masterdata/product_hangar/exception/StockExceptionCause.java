package com.app.masterdata.product_hangar.exception;

import org.springframework.http.HttpStatus;

import com.app.base.exception.ApplicationExceptionCause;

public enum StockExceptionCause implements ApplicationExceptionCause {
	NOT_FOUND("No stock entries found for your request", HttpStatus.NOT_FOUND),
	NOT_FOUND_VALID("No stock entries with a valid price found", HttpStatus.NOT_FOUND);
	
	private String code;
	private HttpStatus httpStatus;
	
	StockExceptionCause(String code, HttpStatus httpStatus) {
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
