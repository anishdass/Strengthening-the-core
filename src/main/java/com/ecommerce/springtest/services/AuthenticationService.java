package com.ecommerce.springtest.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//The primary job of this class to verify the user
@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final JwtService jwtService;

    public boolean authenticate(String username, String token){
        return !jwtService.isTokenExpired(token) && jwtService.isTokenValid(username, token);
    }
}
