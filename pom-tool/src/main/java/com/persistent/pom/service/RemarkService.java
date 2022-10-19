package com.persistent.pom.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.pom.entities.Remark;
import com.persistent.pom.repositories.RemarkRepo;

@Component
public class RemarkService {
	
	@Autowired
	RemarkRepo remarksRepo;
	
	public List<Remark> getRemarks() throws IllegalArgumentException {
		return (List<Remark>) remarksRepo.findAll();
	}
	
	public Optional<Remark> getRemark(int id) throws IllegalArgumentException {
		return remarksRepo.findById(id);
	}
	
	public Remark updateRemark(Remark remarks) throws IllegalArgumentException {
		return remarksRepo.save(remarks);
	}
	public Remark addRemark(Remark remarks) throws IllegalArgumentException {
		return remarksRepo.save(remarks);
	}
	public void deleteRemark(int id) throws IllegalArgumentException {
		remarksRepo.deleteById(id);
	}
	

	
}
