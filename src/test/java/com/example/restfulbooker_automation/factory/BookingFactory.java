package com.example.restfulbooker_automation.factory;

import com.example.restfulbooker_automation.models.BookingDates;
import com.example.restfulbooker_automation.models.BookingRequest;

public class BookingFactory {

    public static BookingRequest createValidBooking() {
        return BookingRequest.builder()
                .firstname("John")
                .lastname("Doe")
                .totalprice(150)
                .depositpaid(true)
                .bookingdates(
                        BookingDates.builder()
                                .checkin("2024-01-01")
                                .checkout("2024-01-10")
                                .build()
                )
                .additionalneeds("Breakfast")
                .build();
    }
}
