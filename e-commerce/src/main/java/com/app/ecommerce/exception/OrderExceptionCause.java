package com.app.ecommerce.exception;

import org.springframework.http.HttpStatus;

import com.app.base.exception.ApplicationExceptionCause;

public enum OrderExceptionCause implements ApplicationExceptionCause {
	ITEM_UNEXPECTED_ERROR("Unexpected error while trying to save item", HttpStatus.BAD_REQUEST),
	ORDER_TOTALS_ERROR("Could not update order totals", HttpStatus.BAD_REQUEST),
	STOCK_UPDATE_ERROR("Could not update stock", HttpStatus.BAD_REQUEST),
	ORDER_NOT_FOUND("Order not found", HttpStatus.NOT_FOUND),
	ITEM_NOT_FOUND("The item does not exist", HttpStatus.NOT_FOUND),
	NOT_ENOUGH_STOCK("Not enough stock of this item available", HttpStatus.CONFLICT);
	
	private String code;
	private HttpStatus httpStatus;
	
	OrderExceptionCause(String code, HttpStatus httpStatus) {
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
