package dev.andrewjfei.user.management.api.example.exceptions;

import dev.andrewjfei.user.management.api.example.enums.Error;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class UserManagementApiExampleException extends RuntimeException {

    private Error error;

    private HttpStatus httpStatus;

    public UserManagementApiExampleException() {

    }

    public UserManagementApiExampleException(Error error, HttpStatus httpStatus) {
        this.error = error;
        this.httpStatus = httpStatus;
    }

}
