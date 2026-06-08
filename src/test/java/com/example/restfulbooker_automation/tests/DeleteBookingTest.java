package com.example.restfulbooker_automation.tests;

import org.junit.jupiter.api.Test;

import com.example.restfulbooker_automation.factory.AuthFactory;
import com.example.restfulbooker_automation.factory.BookingFactory;
import com.example.restfulbooker_automation.models.AuthRequest;
import com.example.restfulbooker_automation.models.BookingRequest;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteBookingTest extends BaseTest {

    @Test
    void shouldDeleteBookingSuccessfully() {
        // Arrange - Create a booking
        BookingRequest request = BookingFactory.createValidBooking();
        Response createResponse = bookingClient.createBooking(request);
        int bookingId = createResponse.jsonPath().getInt("bookingid");

        // Arrange - Chain Auth to get Token
        AuthRequest authRequest = AuthFactory.createValidAuth();
        Response authResponse = bookingClient.createToken(authRequest);
        String token = authResponse.jsonPath().getString("token");

        // Act
        Response deleteResponse = bookingClient.deleteBooking(bookingId, token);

        // Assert Delete Action
        assertThat(deleteResponse.statusCode()).isEqualTo(201); // Restful-booker spec uses 201 for delete

        // Assert Verification
        Response getResponse = bookingClient.getBooking(bookingId);
        assertThat(getResponse.statusCode()).isEqualTo(404);
    }
}
