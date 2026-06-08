package com.example.restfulbooker_automation.tests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.restfulbooker_automation.client.BookingClient;
import com.example.restfulbooker_automation.factory.BookingFactory;
import com.example.restfulbooker_automation.models.BookingRequest;

import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreateBookingTest {

    @Autowired
    private BookingClient bookingClient;

    @Test
    void shouldCreateBookingSuccessfully() {
        // Arrange
        BookingRequest request = BookingFactory.createValidBooking();

        // Act
        Response response = bookingClient.createBooking(request);

        // Assert
        assertThat(response.statusCode()).isEqualTo(200);

        int bookingId = response.jsonPath().getInt("bookingid");
        assertThat(bookingId).isGreaterThan(0);

        assertThat(response.jsonPath().getString("booking.firstname"))
                .isEqualTo(request.getFirstname());

        assertThat(response.jsonPath().getString("booking.lastname"))
                .isEqualTo(request.getLastname());

        assertThat(response.jsonPath().getInt("booking.totalprice"))
                .isEqualTo(request.getTotalprice());

        assertThat(response.jsonPath().getBoolean("booking.depositpaid"))
                .isEqualTo(request.isDepositpaid());
    }
}
