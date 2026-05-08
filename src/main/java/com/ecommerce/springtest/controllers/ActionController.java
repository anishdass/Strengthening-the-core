package com.ecommerce.springtest.controllers;

import com.ecommerce.springtest.dtos.ActionResponse;
import com.ecommerce.springtest.dtos.PingRequest;
import com.ecommerce.springtest.services.ActionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/actions")
@RequiredArgsConstructor
public class ActionController {

    private final ActionService actionService;

    @GetMapping("/ping")
    public ResponseEntity<ActionResponse> ping(@RequestBody PingRequest request){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(actionService.ping(request.username(), request.token()));
    }
}
