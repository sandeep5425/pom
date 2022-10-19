package com.persistent.pom.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.entities.Roles;
import com.persistent.pom.response.ResponseMessage;
import com.persistent.pom.service.RolesService;

@RestController
public class RolesController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RolesController.class);

	@Autowired
	RolesService rolesService;

	@GetMapping(value = "/roles")
	public ResponseEntity<ResponseMessage<List<Roles>>> getRoles() throws Exception {
		LOGGER.info("Entered Roles controller to get the list of roles :: method = getRoles");
		List<Roles> roles = rolesService.getRoles();
		ResponseMessage<List<Roles>> response = new ResponseMessage<>();
		if (roles.size() == 0) {
			response.setMessage("No roles found");
			response.setData(new ArrayList<>());
			response.setLength(0);
			LOGGER.info("Exit Roles controller  as no roles found :: method = getRoles");
			return new ResponseEntity<ResponseMessage<List<Roles>>>(response, HttpStatus.NO_CONTENT);
		}

		response.setLength(roles.size());
		response.setMessage("List of roles");
		response.setData(roles);
		response.setStatusCode(HttpStatus.OK);
		LOGGER.info("Exit Roles controller with list of roles :: method = getRoles");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
