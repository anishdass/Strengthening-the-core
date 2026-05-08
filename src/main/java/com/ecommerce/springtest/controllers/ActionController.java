package com.ecommerce.springtest.controllers;

import com.ecommerce.springtest.services.ActionService;
import lombok.RequiredArgsConstructor;
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
    public String ping(@RequestBody String token){
        return actionService.ping(token);
    }
}
