package com.ecommerce.springtest.services;

import com.ecommerce.springtest.repositories.MainRepository;
import com.ecommerce.springtest.models.User;
import com.ecommerce.springtest.dtos.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final MainRepository mainRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthResponse signup(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = mainRepository.save(user);

        String jwtToken = jwtService.generateToken(savedUser);

        return AuthResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .build();
    }
}
