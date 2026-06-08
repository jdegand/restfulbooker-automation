package com.example.restfulbooker_automation.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.restfulbooker_automation.client.BookingClient;
import com.example.restfulbooker_automation.factory.BookingFactory;
import com.example.restfulbooker_automation.models.BookingRequest;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookingClient.class})
@TestPropertySource(locations = "classpath:application.properties")
class GetBookingTest {

    @Autowired
    private BookingClient bookingClient;

    @Test
    void shouldGetBookingSuccessfully() {
        // Arrange
        BookingRequest request = BookingFactory.createValidBooking();
        Response createResponse = bookingClient.createBooking(request);
        int bookingId = createResponse.jsonPath().getInt("bookingid");

        // Act
        Response response = bookingClient.getBooking(bookingId);

        // Assert
        assertThat(response.statusCode()).isEqualTo(200);
        assertThat(response.jsonPath().getString("firstname"))
                .isEqualTo(request.getFirstname());
        assertThat(response.jsonPath().getString("lastname"))
                .isEqualTo(request.getLastname());
        assertThat(response.jsonPath().getInt("totalprice"))
                .isEqualTo(request.getTotalprice());
        assertThat(response.jsonPath().getBoolean("depositpaid"))
                .isEqualTo(request.isDepositpaid());
    }
}
