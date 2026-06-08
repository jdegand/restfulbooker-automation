package com.example.restfulbooker_automation.client;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookingClient {

    @Value("${restfulbooker.base-url}")
    private String baseUrl;

    public Response createBooking(Object requestBody) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .body(requestBody)
                .post("/booking")
                .then()
                .extract()
                .response();
    }

    public Response getBooking(int bookingId) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .get("/booking/" + bookingId)
                .then()
                .extract()
                .response();
    }

    public Response updateBooking(int bookingId, Object requestBody, String token) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .contentType("application/json")
                .header("Cookie", "token=" + token)
                .body(requestBody)
                .put("/booking/" + bookingId)
                .then()
                .extract()
                .response();
    }

    public Response deleteBooking(int bookingId, String token) {
        return RestAssured.given()
                .baseUri(baseUrl)
                .header("Cookie", "token=" + token)
                .delete("/booking/" + bookingId)
                .then()
                .extract()
                .response();
    }
}
