package dev.andrewjfei.user.management.api.example.daos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserDAOTest {

    private UserDAO userDAO;

    @Test
    public void testEmptyConstructor_returnsCorrectUserDAO() {
        userDAO = new UserDAO();

        assertNull(userDAO.getId());
        assertNull(userDAO.getUsername());
        assertNull(userDAO.getFirstName());
        assertNull(userDAO.getLastName());
        assertNull(userDAO.getPassword());
        assertNull(userDAO.getCreated());
    }

    @Test
    public void testNonEmptyConstructor_returnsCorrectUserDAO() {
        String username = "joesmith";
        String firstName = "Joe";
        String lastName = "Smith";
        String password = "password";

        userDAO = new UserDAO(username, firstName, lastName, password);

        assertNull(userDAO.getId());
        assertEquals(username, userDAO.getUsername());
        assertEquals(firstName, userDAO.getFirstName());
        assertEquals(lastName, userDAO.getLastName());
        assertEquals(password, userDAO.getPassword());
        assertNotNull(userDAO.getCreated());
    }

}
