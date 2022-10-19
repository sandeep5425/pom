package com.persistent.pom.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.config.JwtToken;
import com.persistent.pom.config.TokenResponse;
import com.persistent.pom.config.UserRequest;
import com.persistent.pom.response.ResponseMessage;

@RestController
public class AuthorizeUserController {

	@Autowired
	JwtToken jwtToken;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizeUserController.class);

	@Value("#{'${pom.admin.list}'.split(',')}")
	private List<String> admins;

	@PostMapping(value = "/authorize")
	public ResponseEntity<ResponseMessage<TokenResponse>> authorize(@RequestBody UserRequest user) throws Exception {
		LOGGER.info("Entered Authorize user controller :: method = authorize");
		// authenticate user
		// for only valid users
		ResponseMessage<TokenResponse> response = new ResponseMessage<>();
		HashMap<String, String> claims = new HashMap<>();
		String username = user.getUsername();
		if (admins.contains(username)) {
			claims.put(username, "Admin");
			response.setMessage("An admin user logged");
			LOGGER.info("Got the employee role as admin");
		} else {
			claims.put(username, "Lead/Developer"); // need to confirm
		}

		String token = jwtToken.generateJWTToken(claims, user);


		TokenResponse tokenResponse = new TokenResponse();
		tokenResponse.setUsername(username);
		tokenResponse.setToken(token);

		response.setData(tokenResponse);
		response.setStatusCode(HttpStatus.OK);
		response.setLength(1);
		response.setToken(tokenResponse);
		return new ResponseEntity<ResponseMessage<TokenResponse>>(response, HttpStatus.OK);
	}

}
