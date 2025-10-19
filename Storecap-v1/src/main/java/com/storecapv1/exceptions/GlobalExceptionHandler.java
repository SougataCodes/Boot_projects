package com.storecapv1.exceptions;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(DuplicateRegisterEmailException.class)
	public ResponseEntity<Map<String, Object>> handleDuplicateEmail(DuplicateRegisterEmailException ex) {
		
		Map<String, Object> error = new HashMap<>();
		
		error.put("timestamp", LocalDateTime.now());
		error.put("status", HttpStatus.BAD_REQUEST.value());
		error.put("error", "Duplicate Email");
		error.put("message", ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(DuplicateRegisterMobileException.class)
	public ResponseEntity<Map<String, Object>> handleDuplicateMobile(DuplicateRegisterMobileException ex) {
		
		Map<String, Object> error = new HashMap<>();
		
		error.put("timestamp", LocalDateTime.now());
		error.put("status", HttpStatus.BAD_REQUEST.value());
		error.put("error", "Duplicate Mobile");
		error.put("message", ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, Object>> handleGlobal(Exception ex) {
		
		Map<String, Object> error = new HashMap<>();
		
		error.put("timestamp", LocalDateTime.now());
		error.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.put("error", "Sever Error");
		error.put("message", ex.getMessage());
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
