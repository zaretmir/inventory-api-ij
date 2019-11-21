package com.app.base.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

public interface ApplicationExceptionCause extends Serializable {
	
	String getCode();

	HttpStatus getHttpStatus();
	
	

}
