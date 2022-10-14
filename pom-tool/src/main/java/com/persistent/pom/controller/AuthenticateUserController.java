package com.persistent.pom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.persistent.pom.config.JwtToken;
import com.persistent.pom.config.TokenResponse;
import com.persistent.pom.config.UserRequest;

@RestController
public class AuthenticateUserController {
	
	@Autowired
	JwtToken jwtToken;
	
	@PostMapping(value = "/authorize")
	public TokenResponse authenticate(@RequestBody UserRequest user) {
		//authenticate user
		//for only valid users
		String token = jwtToken.generateJWTToken(user);
		TokenResponse tokenResponse = new TokenResponse();
		tokenResponse.setUsername(user.getUsername());
		tokenResponse.setToken(token);
		return tokenResponse;
	}
	
	@PostMapping(value = "/validateToken")
	public String validateToken(@RequestBody TokenResponse token) {
		UserRequest user = new UserRequest();
		user.setUsername(token.getUsername());
		if (jwtToken.validateToken(user, token.getToken()))
			return "Valid Token";
		return "Token is invalid";
	}
	
}
