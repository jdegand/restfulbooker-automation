package com.example.restfulbooker_automation.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthClient {

    @Value("${restfulbooker.base-url}")
    private String baseUrl;

    public Response createToken(Object requestBody) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(requestBody)
                .post("/auth")
                .then()
                .extract()
                .response();
    }
}
