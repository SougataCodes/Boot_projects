package com.storecapv1.exceptions;

@SuppressWarnings("serial")
public class DuplicateRegisterMobileException extends RuntimeException {

	public DuplicateRegisterMobileException(String message) {
		super(message);
	}
}