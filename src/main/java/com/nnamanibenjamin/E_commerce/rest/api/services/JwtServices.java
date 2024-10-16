package com.nnamanibenjamin.E_commerce.rest.api.services;

import java.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service 
public class JwtServices {
    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    public String generateToken(UserDetails userDetails){

        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername()); 
    }

    public String createToken(Map<String, Object> claims, String subject){ 

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, secret.getBytes())
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token){
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    public Boolean isTokenExpired(String token){
        final Date expiration = Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }
}
