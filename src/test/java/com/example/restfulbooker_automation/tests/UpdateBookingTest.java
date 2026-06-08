package com.example.restfulbooker_automation.tests;

import org.junit.jupiter.api.Test;

import com.example.restfulbooker_automation.factory.AuthFactory;
import com.example.restfulbooker_automation.factory.BookingFactory;
import com.example.restfulbooker_automation.models.AuthRequest;
import com.example.restfulbooker_automation.models.BookingRequest;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

class UpdateBookingTest extends BaseTest {

    @Test
    void shouldUpdateBookingSuccessfully() {
        // Arrange - Create a booking
        BookingRequest originalRequest = BookingFactory.createValidBooking();
        Response createResponse = bookingClient.createBooking(originalRequest);
        int bookingId = createResponse.jsonPath().getInt("bookingid");

        // Arrange - Chain Auth to get Token
        AuthRequest authRequest = AuthFactory.createValidAuth();
        Response authResponse = bookingClient.createToken(authRequest);
        String token = authResponse.jsonPath().getString("token");

        // Arrange - Prepare update body
        BookingRequest updateRequest = BookingFactory.createValidBooking();
        updateRequest.setFirstname("Jane");
        updateRequest.setLastname("Smith");

        // Act
        Response response = bookingClient.updateBooking(bookingId, updateRequest, token);

        // Assert
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("firstname"))
                .isEqualTo(updateRequest.getFirstname());
        assertThat(response.jsonPath().getString("lastname"))
                .isEqualTo(updateRequest.getLastname());
    }
}
