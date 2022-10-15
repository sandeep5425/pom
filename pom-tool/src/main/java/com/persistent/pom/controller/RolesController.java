package com.persistent.pom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.entities.Project;
import com.persistent.pom.entities.Roles;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.RolesService;

@RestController
public class RolesController {

	@Autowired
	RolesService rolesService;

	@GetMapping(value = "/roles")
	public ResponseEntity<ResponseMessage<List<Roles>>> getRoles() {
		List<Roles> roles = rolesService.getRoles();
		ResponseMessage<List<Roles>> response = new ResponseMessage<>();

		response.setLength(roles.size());
		response.setMessage("List of roles");
		response.setData(roles);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
