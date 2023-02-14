package dev.andrewjfei.user.management.api.example.enums;

import lombok.Getter;

@Getter
public enum Error {
    UNEXPECTED_ERROR(0, "An unexpected error has occurred."),
    USER_NOT_FOUND(1, "The user does not exist."),
    USER_FRIEND_REQUEST_ERROR(2, "The user either has a current pending friend request or is already friends."),
    FRIENDSHIP_NOT_FOUND(3, "The friendship between the requester and receiver does not exist"),
    USER_FRIEND_REQUEST_NOT_PENDING_ERROR(4, "The requester and receiver are already friends and have no pending friend request");

    private final int errorCode;
    private final String message;

    Error(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }


}
