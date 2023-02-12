package dev.andrewjfei.user.management.api.example.daos;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

import static dev.andrewjfei.user.management.api.example.utils.FriendshipUtil.generateRandomFriendshipDao;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserDaoTest {

    private UserDao userDao;

    @Test
    public void testEmptyConstructor_returnsCorrectUserDao() {
        userDao = new UserDao();

        assertNull(userDao.getId());
        assertNull(userDao.getUsername());
        assertNull(userDao.getFirstName());
        assertNull(userDao.getLastName());
        assertNull(userDao.getPassword());
        assertNull(userDao.getCreated());
    }

    @Test
    public void testNonEmptyConstructor_returnsCorrectUserDao() {
        String username = "joesmith";
        String firstName = "Joe";
        String lastName = "Smith";
        String password = "password";

        userDao = new UserDao(username, firstName, lastName, password);

        assertNull(userDao.getId());
        assertEquals(username, userDao.getUsername());
        assertEquals(firstName, userDao.getFirstName());
        assertEquals(lastName, userDao.getLastName());
        assertEquals(password, userDao.getPassword());
        assertNotNull(userDao.getCreated());
    }

    @Test
    public void testSetters() {
        UUID id = UUID.randomUUID();
        String username = "joesmith";
        String firstName = "Joe";
        String lastName = "Smith";
        String password = "password";
        LocalDateTime created = LocalDateTime.now();

        FriendshipDao friendshipDao = generateRandomFriendshipDao();

        List<FriendshipDao> friendshipDaoList = Collections.singletonList(friendshipDao);

        userDao = new UserDao();

        userDao.setId(id);
        userDao.setUsername(username);
        userDao.setFirstName(firstName);
        userDao.setLastName(lastName);
        userDao.setPassword(password);
        userDao.setCreated(created);
        userDao.setFriendships(friendshipDaoList);

        assertEquals(id, userDao.getId());
        assertEquals(username, userDao.getUsername());
        assertEquals(firstName, userDao.getFirstName());
        assertEquals(lastName, userDao.getLastName());
        assertEquals(password, userDao.getPassword());
        assertEquals(created, userDao.getCreated());

        assertEquals(friendshipDaoList.size(), userDao.getFriendships().size());

        for (FriendshipDao friendship : userDao.getFriendships()) {
            assertTrue(friendshipDaoList.contains(friendship));
        }
    }

    @Test
    public void testGetFriends_returnsCorrectList() {
        FriendshipDao friendshipDao = generateRandomFriendshipDao();
        friendshipDao.setAccepted(true);

        List<FriendshipDao> friendshipDaoList = Collections.singletonList(friendshipDao);

        userDao = new UserDao();
        userDao.setFriendships(friendshipDaoList);

        assertEquals(friendshipDaoList.size(), userDao.getFriends().size());

        for (FriendshipDao friendship : userDao.getFriends()) {
            assertTrue(friendshipDaoList.contains(friendship));
        }
    }

    @Test
    public void testGetFriendRequests_returnsCorrectList() {
        FriendshipDao friendshipDao = generateRandomFriendshipDao();

        List<FriendshipDao> friendshipDaoList = Collections.singletonList(friendshipDao);

        userDao = new UserDao();
        userDao.setFriendships(friendshipDaoList);

        assertEquals(friendshipDaoList.size(), userDao.getFriendRequests().size());

        for (FriendshipDao friendship : userDao.getFriendRequests()) {
            assertTrue(friendshipDaoList.contains(friendship));
        }
    }

}
