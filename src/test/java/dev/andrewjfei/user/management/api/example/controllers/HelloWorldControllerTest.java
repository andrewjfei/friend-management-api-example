package dev.andrewjfei.user.management.api.example.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

public class HelloWorldControllerTest {

    private HelloWorldController helloWorldController;

    public HelloWorldControllerTest() {
        this.helloWorldController = new HelloWorldController();
    }

    @Test
    public void testHelloWorld_returnsCorrectString() {
        String expected = "Hello World!";

        ResponseEntity<String> response = helloWorldController.helloWorld();

        assertEquals(OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

}
