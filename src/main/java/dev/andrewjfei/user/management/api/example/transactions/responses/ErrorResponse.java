package dev.andrewjfei.user.management.api.example.transactions.responses;

public record ErrorResponse(
        int errorCode,
        String message
) {
}
