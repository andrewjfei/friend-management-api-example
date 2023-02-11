package dev.andrewjfei.user.management.api.example.exceptions;

import dev.andrewjfei.user.management.api.example.transactions.responses.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static dev.andrewjfei.user.management.api.example.enums.Error.UNEXPECTED_ERROR;
import static dev.andrewjfei.user.management.api.example.utils.MapperUtil.toErrorResponse;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class UserManagementApiExampleExceptionHandler {

    private final Logger LOGGER = LoggerFactory.getLogger(UserManagementApiExampleExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        if (exception instanceof UserManagementApiExampleException) {
            UserManagementApiExampleException userManagementApiExampleException = (UserManagementApiExampleException) exception;
            return new ResponseEntity<>(
                    toErrorResponse(userManagementApiExampleException.getError()),
                    userManagementApiExampleException.getHttpStatus()
            );
        } else {
            LOGGER.error(exception.getMessage());
            return new ResponseEntity<>(toErrorResponse(UNEXPECTED_ERROR), INTERNAL_SERVER_ERROR);
        }
    }

}
