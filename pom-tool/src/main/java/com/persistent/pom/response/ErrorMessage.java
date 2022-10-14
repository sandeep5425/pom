package com.persistent.pom.response;

import org.springframework.http.HttpStatus;

public class ErrorMessage {
	private HttpStatus status;
	private String errrorMessage;
	private String messageDescription;
	
	
	public ErrorMessage() {
		super();
	}
	public ErrorMessage(HttpStatus status, String errrorMessage, String messageDescription) {
		super();
		this.status = status;
		this.errrorMessage = errrorMessage;
		this.messageDescription = messageDescription;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public String getErrrorMessage() {
		return errrorMessage;
	}
	public void setErrrorMessage(String errrorMessage) {
		this.errrorMessage = errrorMessage;
	}
	public String getMessageDescription() {
		return messageDescription;
	}
	public void setMessageDescription(String messageDescription) {
		this.messageDescription = messageDescription;
	}
	
	
	
	

}
