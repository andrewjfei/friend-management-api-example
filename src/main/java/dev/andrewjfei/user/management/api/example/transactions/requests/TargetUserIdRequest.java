package dev.andrewjfei.user.management.api.example.transactions.requests;

public record TargetUserIdRequest(
        String userId,
        String targetUserId
) {
}
