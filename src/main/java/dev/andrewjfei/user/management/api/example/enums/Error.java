package dev.andrewjfei.user.management.api.example.enums;

import lombok.Getter;

@Getter
public enum Error {
    UNEXPECTED_ERROR(0, "An unexpected error has occurred."),
    USER_NOT_FOUND(1, "The user ID provided does not exist.");

    private final int errorCode;
    private final String message;

    Error(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }


}
