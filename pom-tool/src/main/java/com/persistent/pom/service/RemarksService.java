package com.persistent.pom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.pom.entities.Remarks;
import com.persistent.pom.repositories.RemarksRepo;

@Component
public class RemarksService {
	
	@Autowired
	RemarksRepo remarksRepo;
	
	public void getRemarks() {
	}
	
	public void getRemark(int id) {
		
	}
	
	public void updateRemark(Remarks remarks) {
		
	}
	

	
}
