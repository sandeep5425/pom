package com.persistent.pom.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.persistent.pom.response.ErrorMessage;

@ControllerAdvice
public class NullValueInsertion extends RuntimeException {
	@ExceptionHandler(value = NullValueInsertion.class)
	public ResponseEntity<Object> exception(NullValueInsertion exception) {
		ErrorMessage error = new ErrorMessage();
		error.setErrrorMessage("Add Complete and Meaningfull Data");
		return new ResponseEntity<>(error, HttpStatus.NOT_ACCEPTABLE);
	}
}
