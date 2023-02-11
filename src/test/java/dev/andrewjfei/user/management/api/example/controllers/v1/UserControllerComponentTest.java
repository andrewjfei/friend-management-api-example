package dev.andrewjfei.user.management.api.example.controllers.v1;

import dev.andrewjfei.user.management.api.example.controllers.BaseComponentTest;
import dev.andrewjfei.user.management.api.example.services.v1.UserFriendsService;
import dev.andrewjfei.user.management.api.example.transactions.requests.UserIdRequest;
import dev.andrewjfei.user.management.api.example.transactions.responses.BasicUserResponse;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpStatus.OK;

public class UserControllerComponentTest extends BaseComponentTest {

    @Autowired
    private UserController userController;

    @Autowired
    private UserFriendsService userFriendsService;

    // Joe Smith
    private final String USER_ID = "11afa5b4-c622-4320-a4e9-7c374172b63d";

    private final int NUM_OF_FRIENDS = 1;

    public UserControllerComponentTest() {

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
    public void testFetchAllFriends_returnsCorrectResponse() {
        // Given
        UserIdRequest request = new UserIdRequest(USER_ID);

        // When
        ResponseEntity<List<BasicUserResponse>> response = userController.fetchAllFriends(request);

        // Then
        List<BasicUserResponse> userFriendsList = response.getBody();

        assertEquals(OK, response.getStatusCode());
        assertEquals(NUM_OF_FRIENDS, userFriendsList.size());
    }

    @Test
    public void testRemoveFriend_returnsCorrectString() {
        String expected = "Friend Removed.";

        ResponseEntity<String> response = userController.removeFriend();

        assertEquals(OK, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

}
