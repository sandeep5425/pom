package com.persistent.pom.response;

import org.springframework.http.HttpStatus;

import com.persistent.pom.config.TokenResponse;

public class ResponseMessage<T> {
	
	private HttpStatus statusCode;
	private T data;
	private int length;
	private String message;
	private TokenResponse token;
		
	public ResponseMessage() {
		super();
	}
	public ResponseMessage(HttpStatus statusCode, T data, int length, String message) {
		super();
		this.statusCode = statusCode;
		this.data = data;
		this.length = length;
		this.message = message;
	}
	public ResponseMessage(HttpStatus statusCode, T data, String message, TokenResponse token) {
		super();
		this.statusCode = statusCode;
		this.data = data;
		this.message = message;
		this.token = token;
	}
	public ResponseMessage(HttpStatus statusCode, T data) {
		super();
		this.statusCode = statusCode;
		this.data = data;
	}
	
	public ResponseMessage(HttpStatus statusCode, T data, int length, String message, TokenResponse token) {
		super();
		this.statusCode = statusCode;
		this.data = data;
		this.length = length;
		this.message = message;
		this.token = token;
	}
	public HttpStatus getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(HttpStatus statusCode) {
		this.statusCode = statusCode;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public TokenResponse getToken() {
		return token;
	}
	public void setToken(TokenResponse token) {
		this.token = token;
	}
}
