package com.persistent.pom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.entities.Remarks;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.RemarksService;

@RestController
public class RemarksController {
	@Autowired
	private RemarksService remarksService;
	
	@GetMapping(value = "/remarks")
	public ResponseEntity<ResponseMessage<Object>> getRemarks(){
		return null;
	}
	
	@PostMapping()
	public ResponseEntity<Object> updateRemarks(@ResponseBody Remarks remarks){
		return null;
	}
	
}
