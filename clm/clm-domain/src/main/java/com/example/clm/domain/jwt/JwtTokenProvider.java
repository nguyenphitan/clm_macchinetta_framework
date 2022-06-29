package com.example.clm.domain.jwt;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.clm.domain.model.CustomUserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {

	private final String JWT_SECRET = "atrs";

	private final long JWT_EXPIRATION = 604800000L;

	public String generateToken(CustomUserDetails userDetails) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		return Jwts.builder()
					.setSubject(Long.toString(userDetails.getUser().getId()))
					.setIssuedAt(now)
					.setExpiration(expiryDate)
					.signWith(SignatureAlgorithm.HS512, JWT_SECRET)
					.compact();
	}
	
	public String getUsernameFromJWT(String token) {
		String username = Jwts.parser()
							.setSigningKey(JWT_SECRET)
							.parseClaimsJws(token)
							.getBody()
							.getSubject();
		
		return username;
	}
	
	// Validate token
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty.");
		} catch (ExpiredJwtException e) {
			log.error("Expired JWT token.");
		} catch (MalformedJwtException e) {
			log.error("Invalid JWT token.");
		} catch (UnsupportedJwtException e) {
			log.error("Unsupported JWT token.");
		}
		return false;
	}

}
