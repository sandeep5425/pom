package com.persistent.pom.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NoSuchIDException extends RuntimeException{
	
	 @ExceptionHandler(value = NoSuchIDException.class)
	 public ResponseEntity<Object> exception(NoSuchIDException exception) {
	      return new ResponseEntity<>("Id Not Found", HttpStatus.NOT_FOUND);
	 }
	
}
