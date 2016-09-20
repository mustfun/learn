/*
 * Copyright (c) 2009-2016. 上海诺诺镑客 All rights reserved.
 * @(#) ValidationException.java 下午4:15:03
 */
package com.dzy.validation.exception;

import java.io.Serializable;

public class ValidationException extends RuntimeException implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String message;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ValidationException(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}
	
}
