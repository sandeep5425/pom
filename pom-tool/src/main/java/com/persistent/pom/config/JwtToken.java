package com.persistent.pom.config;

import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtToken {
	private static final int EXPIRY_TIME = 10; // TIME A TOKEN IS VALID
	private static final String SECRET_KEY = "SANDEEPMADHIRIPERSISTENTCOMSANDEEPMADHIRIPERSISTENTCOMSANDEEPMADHIRIPERSISTENTCOMSANDEEPMADHIRIPERSISTENTCOM";

	public String generateJWTToken(UserRequest user) {
		// add claims for users
		return createToken(user.getUsername());
	}

	public String createToken(String username) {
		Date currentDate = new Date(System.currentTimeMillis());
		Date expiryDate = new Date(System.currentTimeMillis() + EXPIRY_TIME);
		return Jwts.builder().setSubject(username).setIssuedAt(currentDate).setExpiration(expiryDate).signWith(SignatureAlgorithm.HS256	, SECRET_KEY).compact();
	}

	public Claims claims(String token) {
		return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
	}

	public boolean validateToken(UserRequest user, String token) {
		String username = claims(token).getSubject();
		return user.getUsername().equals(username) && !isTokenExpired(token);

	}

	public boolean isTokenExpired(String token) {
		return claims(token).getExpiration().before(new Date());

	}

}
