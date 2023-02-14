package dev.andrewjfei.user.management.api.example.transactions.requests;

public record FriendRequestResponseRequest(
        String receiverId,
        String requesterId,
        boolean hasAccepted
) {
}
