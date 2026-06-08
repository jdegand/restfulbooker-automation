package com.example.restfulbooker_automation.tests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.restfulbooker_automation.client.BookingClient;
import com.example.restfulbooker_automation.factory.AuthFactory;
import com.example.restfulbooker_automation.models.AuthRequest;
import io.restassured.response.Response;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookingClient.class})
@TestPropertySource(locations = "classpath:application.properties")
class AuthTest {

    @Autowired
    private BookingClient bookingClient;

    @Test
    void shouldCreateTokenSuccessfullyWithValidCredentials() {
        // Arrange
        AuthRequest authRequest = AuthFactory.createValidAuth();

        // Act
        Response response = bookingClient.createToken(authRequest);

        // Assert
        assertThat(response.statusCode()).isEqualTo(200);
        
        String token = response.jsonPath().getString("token");
        assertThat(token).isNotNull().isNotEmpty();
    }

    @Test
    void shouldFailToCreateTokenWithInvalidCredentials() {
        // Arrange
        AuthRequest invalidAuth = AuthRequest.builder()
                .username("invalidUser")
                .password("badPassword")
                .build();

        // Act
        Response response = bookingClient.createToken(invalidAuth);

        // Assert
        assertThat(response.statusCode()).isEqualTo(200); // Restful-booker returns 200 with an error message
        
        String reason = response.jsonPath().getString("reason");
        assertThat(reason).isEqualTo("Bad credentials");
    }
}
