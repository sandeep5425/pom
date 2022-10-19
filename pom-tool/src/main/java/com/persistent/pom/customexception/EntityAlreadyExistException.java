package com.persistent.pom.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.persistent.pom.response.ErrorMessage;

@ControllerAdvice
public class EntityAlreadyExistException extends RuntimeException {

	@ExceptionHandler(value = EntityAlreadyExistException.class)
	public ResponseEntity<ErrorMessage> exception(EntityAlreadyExistException exception) {
		ErrorMessage error = new ErrorMessage();
		error.setErrrorMessage("Details already exists");
		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		return new ResponseEntity<ErrorMessage>(error,HttpStatus.UNPROCESSABLE_ENTITY);
	}

}
