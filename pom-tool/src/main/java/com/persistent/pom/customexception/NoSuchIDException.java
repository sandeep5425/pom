package com.persistent.pom.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.persistent.pom.response.ErrorMessage;
import com.persistent.pom.response.ResponseMessage;

@ControllerAdvice
public class NoSuchIDException extends RuntimeException {

	@ExceptionHandler(value = NoSuchIDException.class)
	public ResponseEntity<Object> exception(NoSuchIDException exception) {
		ErrorMessage error = new ErrorMessage();
		error.setErrrorMessage("Element with id didnt found");
		error.setStatus(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

}
	