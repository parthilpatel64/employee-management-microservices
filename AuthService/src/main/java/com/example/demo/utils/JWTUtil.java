package com.example.demo.utils;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.example.demo.model.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	    private final String SECRET = "secret-key";

	    public String generateToken(Users user) {
	        return Jwts.builder()
	                .setSubject(user.getUsername())
	                .claim("roles", user.getRoles())
	                .setIssuedAt(new Date())
	                .setExpiration(
	                    new Date(System.currentTimeMillis() + 3600000))
	                .signWith(SignatureAlgorithm.HS256, SECRET)
	                .compact();
	    }

	    public boolean validateToken(String token) {
	        try {
	            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	    }
	

}
