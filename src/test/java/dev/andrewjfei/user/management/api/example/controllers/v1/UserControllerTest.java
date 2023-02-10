package dev.andrewjfei.user.management.api.example.controllers.v1;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

public class UserControllerTest {

    private UserController userController;

    public UserControllerTest() {
        this.userController = new UserController();
    }

    /********************* User APIs *********************/

    @Test
    public void testBlockUser_returnsCorrectString() {
        String expected = "User blocked.";

        ResponseEntity<String> response = userController.blockUser();

        assertEquals(OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    /********************* User Friends APIs *********************/

    @Test
    public void testAddFriend_returnsCorrectString() {
        String expected = "Friend request sent.";

        ResponseEntity<String> response = userController.addFriend();

        assertEquals(OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    public void testFetchAllFriends_returnsCorrectString() {
        String expected = "Fetched all friends.";

        ResponseEntity<String> response = userController.fetchAllFriends();

        assertEquals(OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

}
