package com.ecommerce.springtest.dtos;

import lombok.Data;

@Data
public class ActionResponse {
    String username;
    boolean authenticated;
}
