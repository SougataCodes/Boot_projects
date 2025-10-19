package com.storecapv1.exceptions;

@SuppressWarnings("serial")
public class DuplicateRegisterEmailException extends RuntimeException {

	public DuplicateRegisterEmailException(String message) {
		super(message);
	}
}
