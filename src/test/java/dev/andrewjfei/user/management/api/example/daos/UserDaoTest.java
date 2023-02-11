package dev.andrewjfei.user.management.api.example.daos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

}
