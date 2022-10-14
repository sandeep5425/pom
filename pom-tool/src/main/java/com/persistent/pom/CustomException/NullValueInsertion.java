package com.persistent.pom.CustomException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NullValueInsertion extends RuntimeException{
	@ExceptionHandler(value = NullValueInsertion.class)
	 public ResponseEntity<Object> exception(NullValueInsertion exception) {
	      return new ResponseEntity<>("Add Complete and Meaningfull Data", HttpStatus.NOT_ACCEPTABLE);
	 }
}
