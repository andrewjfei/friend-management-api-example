package dev.andrewjfei.user.management.api.example.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorTest {

    @Test
    public void testConstructor_returnsCorrectErrorEnum() {
        int errorCode = 0;
        String message = "An unexpected error has occurred.";

        Error error = Error.UNEXPECTED_ERROR;

        assertEquals(errorCode, error.getErrorCode());
        assertEquals(message, error.getMessage());
    }

}
