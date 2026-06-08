package com.example.restfulbooker_automation.factory;

import com.example.restfulbooker_automation.models.AuthRequest;

public class AuthFactory {

    public static AuthRequest createValidAuth() {
        return AuthRequest.builder()
                .username("admin")
                .password("password123")
                .build();
    }
}
