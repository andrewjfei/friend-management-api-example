package dev.andrewjfei.user.management.api.example.enums;

import lombok.Getter;

@Getter
public enum Error {
    UNEXPECTED_ERROR(0, "An unexpected error has occurred."),
    USER_NOT_FOUND(1, "The user ID provided does not exist."),
    USER_FRIEND_REQUEST_ERROR(2, "The user either has a current pending friend request or is already friends.");

    private final int errorCode;
    private final String message;

    Error(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }


}
