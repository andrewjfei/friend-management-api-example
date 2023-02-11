package dev.andrewjfei.user.management.api.example.exceptions;

import dev.andrewjfei.user.management.api.example.enums.Error;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static dev.andrewjfei.user.management.api.example.enums.Error.UNEXPECTED_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class UserManagementApiExampleExceptionTest {

    private UserManagementApiExampleException userManagementApiExampleException;

    @Test
    public void testEmptyConstructor_returnsCorrectUserManagementApiExampleException() {
        userManagementApiExampleException = new UserManagementApiExampleException();

        assertNull(userManagementApiExampleException.getError());
        assertNull(userManagementApiExampleException.getHttpStatus());
    }

    @Test
    public void testNonEmptyConstructor_returnsCorrectUserManagementApiExampleException() {
        Error error = UNEXPECTED_ERROR;
        HttpStatus httpStatus = INTERNAL_SERVER_ERROR;

        userManagementApiExampleException = new UserManagementApiExampleException(error, httpStatus);

        assertEquals(error, userManagementApiExampleException.getError());
        assertEquals(httpStatus, userManagementApiExampleException.getHttpStatus());
    }

    @Test
    public void testSetters() {
        Error error = UNEXPECTED_ERROR;
        HttpStatus httpStatus = INTERNAL_SERVER_ERROR;

        userManagementApiExampleException = new UserManagementApiExampleException();

        userManagementApiExampleException.setError(error);
        userManagementApiExampleException.setHttpStatus(httpStatus);

        assertEquals(error, userManagementApiExampleException.getError());
        assertEquals(httpStatus, userManagementApiExampleException.getHttpStatus());
    }

}
