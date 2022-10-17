package com.persistent.pom.controller;

import java.util.HashMap;
import java.util.List;

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
public class AuthenticateUserController {

	@Autowired
	JwtToken jwtToken;

	@Value("#{'${pom.admin.list}'.split(',')}")
	private List<String> admins;

	@PostMapping(value = "/authorize")
	public ResponseEntity<ResponseMessage<TokenResponse>> authenticate(@RequestBody UserRequest user) {
		// authenticate user
		// for only valid users
		HashMap<String, String> claims = new HashMap<>();
		String username = user.getUsername();
		if (admins.contains(username)) {
			claims.put(username, "Admin");
		} else {
			claims.put(username, "Lead/Developer"); // need to confirm
		}

		String token = jwtToken.generateJWTToken(claims, user);

		ResponseMessage<TokenResponse> response = new ResponseMessage<>();

		TokenResponse tokenResponse = new TokenResponse();
		tokenResponse.setUsername(username);
		tokenResponse.setToken(token);

		response.setData(tokenResponse);
		response.setMessage("Token message");
		response.setStatusCode(HttpStatus.OK);
		response.setLength(1);
		response.setToken(tokenResponse);
		return new ResponseEntity<ResponseMessage<TokenResponse>>(response, HttpStatus.OK);
	}

}
