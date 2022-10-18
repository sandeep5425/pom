package com.persistent.pom.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.persistent.pom.response.ErrorMessage;

@ControllerAdvice
public class InvalidToken extends RuntimeException {

	@ExceptionHandler(value = InvalidToken.class)
	public ResponseEntity<Object> exception(InvalidToken exception) {
		ErrorMessage error = new ErrorMessage();
		error.setErrrorMessage("Invalid Token");
		error.setStatus(HttpStatus.NOT_ACCEPTABLE);
		return new ResponseEntity<>("Invalid Token", HttpStatus.NOT_ACCEPTABLE);
	}

}
