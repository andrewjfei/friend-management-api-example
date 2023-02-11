package dev.andrewjfei.user.management.api.example.daos;

import org.junit.jupiter.api.Test;

import static dev.andrewjfei.user.management.api.example.utils.UserUtil.generateRandomUserDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FriendshipDAOTest {

    private FriendshipDAO friendshipDAO;

    @Test
    public void testEmptyConstructor_returnsCorrectFriendshipDAO() {
        friendshipDAO = new FriendshipDAO();

        assertNull(friendshipDAO.getRequester());
        assertNull(friendshipDAO.getReceiver());
        assertFalse(friendshipDAO.isAccepted());
        assertNull(friendshipDAO.getCreated());
    }

    @Test
    public void testNonEmptyConstructor_returnsCorrectFriendshipDAO() {
        UserDAO requester = generateRandomUserDAO();
        UserDAO receiver = generateRandomUserDAO();

        friendshipDAO = new FriendshipDAO(requester, receiver);

        assertEquals(requester, friendshipDAO.getRequester());
        assertEquals(receiver, friendshipDAO.getReceiver());
        assertFalse(friendshipDAO.isAccepted());
        assertNotNull(friendshipDAO.getCreated());
    }

}
