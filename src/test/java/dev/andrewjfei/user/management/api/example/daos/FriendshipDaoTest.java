package dev.andrewjfei.user.management.api.example.daos;

import org.junit.jupiter.api.Test;

import static dev.andrewjfei.user.management.api.example.utils.UserUtil.generateRandomUserDAO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FriendshipDaoTest {

    private FriendshipDao friendshipDao;

    @Test
    public void testEmptyConstructor_returnsCorrectFriendshipDao() {
        friendshipDao = new FriendshipDao();

        assertNull(friendshipDao.getRequester());
        assertNull(friendshipDao.getReceiver());
        assertFalse(friendshipDao.isAccepted());
        assertNull(friendshipDao.getCreated());
    }

    @Test
    public void testNonEmptyConstructor_returnsCorrectFriendshipDao() {
        UserDao requester = generateRandomUserDAO();
        UserDao receiver = generateRandomUserDAO();

        friendshipDao = new FriendshipDao(requester, receiver);

        assertEquals(requester, friendshipDao.getRequester());
        assertEquals(receiver, friendshipDao.getReceiver());
        assertFalse(friendshipDao.isAccepted());
        assertNotNull(friendshipDao.getCreated());
    }

    @Test
    @SuppressWarnings("ConstantConditions")
    public void testEquals_onSameObjects_returnsTrue() {
        UserDao requester = generateRandomUserDAO();
        UserDao receiver = generateRandomUserDAO();

        friendshipDao = new FriendshipDao(requester, receiver);

        FriendshipDao friendshipDaoCopy = friendshipDao;

        boolean result = friendshipDao.equals(friendshipDaoCopy);

        assertTrue(result);
    }

    @Test
    public void testEquals_onDifferentObjects_returnsFalse() {
        UserDao requester = generateRandomUserDAO();
        UserDao receiver = generateRandomUserDAO();

        UserDao anotherRequester = generateRandomUserDAO();
        UserDao anotherReceiver = generateRandomUserDAO();

        friendshipDao = new FriendshipDao(requester, receiver);

        FriendshipDao anotherFriendshipDao = new FriendshipDao(anotherRequester, anotherReceiver);

        boolean result = friendshipDao.equals(anotherFriendshipDao);

        assertFalse(result);
    }

}
