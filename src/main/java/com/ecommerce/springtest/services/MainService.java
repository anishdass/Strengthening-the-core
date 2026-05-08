package com.ecommerce.springtest.services;

import com.ecommerce.springtest.dtos.AuthResponse;
import com.ecommerce.springtest.dtos.LoginRequest;
import com.ecommerce.springtest.models.User;
import com.ecommerce.springtest.repositories.MainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainService {

    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final MainRepository mainRepository;

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

    public AuthResponse signup(User userDetails) {
        return authenticationService.signup(userDetails);
    }
}
