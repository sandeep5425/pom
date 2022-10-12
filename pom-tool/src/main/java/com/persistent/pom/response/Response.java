package com.persistent.pom.response;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Response {
	public static ResponseEntity<Object> createResponse(String message , HttpStatus httpStatus ,Object responseData){
		Map<String, Object> response = new HashMap<>();
		response.put("message", message);
		response.put("statusCode", httpStatus.value());
		response.put("data", responseData);
		return new ResponseEntity<Object>(response, httpStatus);
	}
}
