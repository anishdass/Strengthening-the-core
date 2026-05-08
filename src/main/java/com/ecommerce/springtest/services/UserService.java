package com.ecommerce.springtest.services;

import com.ecommerce.springtest.dtos.AuthResponse;
import com.ecommerce.springtest.dtos.LoginRequest;
import com.ecommerce.springtest.models.User;
import com.ecommerce.springtest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JwtService jwtService;
    private final UserRepository mainRepository;
    private final PasswordEncoder passwordEncoder;


    public AuthResponse login(LoginRequest request) {
        Optional<User> user = mainRepository.findByUsername(request.username());

        if (user.isEmpty()) {
            return AuthResponse.builder()
                    .username(request.username())
                    .message("User not found")
                    .token(null)
                    .build();
        }

        String jwtToken = jwtService.generateToken(user.get());

        return AuthResponse.builder()
                .username(request.username())
                .token(jwtToken)
                .message("Login Successful")
                .build();

    }

    public AuthResponse signup(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = mainRepository.save(user);

        String jwtToken = jwtService.generateToken(savedUser);

        return AuthResponse.builder()
                .token(jwtToken)
                .username(user.getUsername())
                .message("User signed up")
                .build();
    }
}
