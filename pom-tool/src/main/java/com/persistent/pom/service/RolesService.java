package com.persistent.pom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.persistent.pom.entities.Roles;
import com.persistent.pom.repositories.RolesRepo;

@Component
public class RolesService {
	
	@Autowired
	RolesRepo rolesRepo;
	
	public List<Roles> getRoles(){
		return (List<Roles>) rolesRepo.findAll();
	}
	

}
