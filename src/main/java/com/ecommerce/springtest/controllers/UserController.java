package com.ecommerce.springtest.controllers;

import com.ecommerce.springtest.models.User;
import com.ecommerce.springtest.dtos.AuthResponse;
import com.ecommerce.springtest.dtos.LoginRequest;
import com.ecommerce.springtest.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService mainService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody User userDetails){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mainService.signup(userDetails));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(mainService.login(request));
    }
}
