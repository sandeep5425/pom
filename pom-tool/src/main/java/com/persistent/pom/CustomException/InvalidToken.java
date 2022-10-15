package com.persistent.pom.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class InvalidToken extends RuntimeException {
	
	@ExceptionHandler(value = InvalidToken.class)
	public ResponseEntity<Object> exception(InvalidToken exception) {
	    return new ResponseEntity<>("Invalid Token", HttpStatus.NOT_ACCEPTABLE);
	}
		
}
