package com.persistent.pom.config;

import java.util.Date;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.persistent.pom.customexception.InvalidToken;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.SignatureException;

@Service
public class JwtToken {

	private static final int EXPIRY_TIME = 10 * 60 * 60; // TIME A TOKEN IS VALID

	@Value("${pom.jwtSecretKey}")
	private String SECRET_KEY;

	public String generateJWTToken(HashMap<String, String> claims, UserRequest user) throws Exception {
		return createToken(claims, user.getUsername());
	}

	public String createToken(HashMap<String, String> claims, String username) throws Exception {
		Date currentDate = new Date(System.currentTimeMillis());
		Date expiryDate = new Date(System.currentTimeMillis() + EXPIRY_TIME);
		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(currentDate).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Claims claims(String token) throws InvalidToken {
		try {
			return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		} catch (SignatureException e) {
			throw new InvalidToken();
		}
	}

	public boolean validateToken(UserRequest user, String token) {
		String username = claims(token).getSubject();
		return user.getUsername().equals(username) && !isTokenExpired(token);

	}

	public boolean isTokenExpired(String token) {
		return claims(token).getExpiration().before(new Date());
	}

}
