package dev.andrewjfei.user.management.api.example.models;

import dev.andrewjfei.user.management.api.example.daos.UserDao;
import org.junit.jupiter.api.Test;

import static dev.andrewjfei.user.management.api.example.utils.UserUtil.generateRandomUserDao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FriendshipIdTest {

    private FriendshipId friendshipId;

    @Test
    public void testEmptyConstructor_returnsCorrectFriendshipId() {
        friendshipId = new FriendshipId();

        assertNull(friendshipId.getRequester());
        assertNull(friendshipId.getReceiver());
    }

    @Test
    public void testNonEmptyConstructor_returnsCorrectFriendshipId() {
        UserDao requesterDao = generateRandomUserDao();
        UserDao receiverDao = generateRandomUserDao();

        friendshipId = new FriendshipId(requesterDao, receiverDao);

        assertEquals(requesterDao, friendshipId.getRequester());
        assertEquals(receiverDao, friendshipId.getReceiver());
    }

}
