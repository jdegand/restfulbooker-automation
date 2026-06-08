package com.example.restfulbooker_automation.tests;

import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import com.example.restfulbooker_automation.factory.BookingFactory;
import com.example.restfulbooker_automation.models.BookingRequest;
import io.restassured.response.Response;
import java.util.stream.Stream;
import static org.assertj.core.api.Assertions.assertThat;

@Execution(ExecutionMode.CONCURRENT)
class CreateBookingFuzzTest extends BaseTest {

    // 1. Define the stream of fuzz inputs dynamically
    static Stream<String> fuzzStrings() {
        return Stream.of(
            "", 
            " ", 
            "A", 
            "A".repeat(1000),
            "<script>alert(1)</script>", 
            "null", 
            "{\"nested\": true}"
        );
    }

    // 2. Reference the method name in the annotation
    @ParameterizedTest
    @MethodSource("fuzzStrings")
    void shouldHandleFuzzedFirstnamesGracefully(String badFirstname) {
        // Arrange
        BookingRequest request = BookingFactory.createValidBooking();
        request.setFirstname(badFirstname); 

        // Act
        Response response = bookingClient.createBooking(request);

        // Assert
        assertThat(response.statusCode())
            .withFailMessage("API crashed with internal error for firstname: " + badFirstname)
            .isNotEqualTo(500); 
    }
}
