package com.ecommerce.springtest.services;

import com.ecommerce.springtest.models.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXoxMjM0NTY3ODkwMTI=";

//    Extract username
    private String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
//    Extract any claim
    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

//    Extract claims
    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
//    isTokenValid
    public boolean isTokenValid(String username, String token){
        final String extractedUsername= extractUsername(token);
        return extractedUsername.equals(username);
    }

//    isTokenExpired
    public boolean isTokenExpired(String token){
        return extractClaim(token, Claims::getExpiration)
                .before(new Date());
    }

    public String generateToken(User user){
        return Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSignInKey(){
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
