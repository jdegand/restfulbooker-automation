package com.example.restfulbooker_automation.tests;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.restfulbooker_automation.client.BookingClient;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BookingClient.class})
@TestPropertySource(locations = "classpath:application.properties")
public abstract class BaseTest {

    @Autowired
    protected BookingClient bookingClient;
}
