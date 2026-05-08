package com.ecommerce.springtest.services;

import com.ecommerce.springtest.dtos.ActionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActionService {
    private final AuthenticationService authenticationService;

    public ActionResponse ping(String username, String token) {
        ActionResponse actionResponse=new ActionResponse();
        if(authenticationService.authenticate(username, token)) {
            actionResponse.setAuthenticated(true);
            actionResponse.setUsername(username);
        }
        else {
            actionResponse.setAuthenticated(false);
            actionResponse.setUsername(username);
        }
        return actionResponse;
    }
}
