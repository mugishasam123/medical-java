package com.samuel.utils;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;

import com.samuel.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class GenerateJwt {
    

    public static String generateJwt(User foundedUser){
        
        Claims claims = Jwts.claims().setSubject(foundedUser.email);
        claims.put("role", foundedUser.role.name());
        claims.put("email", foundedUser.email);

        Instant now = Instant.now();

        String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";

        Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(secret),
                SignatureAlgorithm.HS256.getJcaName());
        String jwtToken = Jwts.builder()
                .claim("role", foundedUser.role.name())
                .claim("email", foundedUser.email)
                .setSubject(foundedUser.email)
                .setId(foundedUser.id)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(5l, ChronoUnit.HOURS)))
                .signWith(hmacKey)
                .compact();

                return jwtToken;
    }
}
