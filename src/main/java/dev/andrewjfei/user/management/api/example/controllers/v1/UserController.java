package dev.andrewjfei.user.management.api.example.controllers.v1;

import dev.andrewjfei.user.management.api.example.services.v1.UserFriendsService;
import dev.andrewjfei.user.management.api.example.transactions.requests.UserIdRequest;
import dev.andrewjfei.user.management.api.example.transactions.responses.BasicUserResponse;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserFriendsService userFriendsService;

    private final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public UserController() {

    }

    /********************* User APIs *********************/

    @PatchMapping("/block")
    public ResponseEntity<String> blockUser() {
        LOGGER.debug("Hit PATCH /api/v1/user/block endpoint");
        String response = "User blocked.";
        return new ResponseEntity<>(response, OK);
    }

    /********************* User Friends APIs *********************/

    @PostMapping("/friends/add")
    public ResponseEntity<String> addFriend() {
        LOGGER.debug("Hit POST /api/v1/user/friends/add endpoint");
        String response = "Friend request sent.";
        return new ResponseEntity<>(response, OK);
    }

    @GetMapping("/friends")
    public ResponseEntity<List<BasicUserResponse>> fetchAllFriends(@RequestBody UserIdRequest request) {
        LOGGER.debug("Hit GET /api/v1/user/friends endpoint");
        UUID userId = UUID.fromString(request.userId());
        List<BasicUserResponse> response = userFriendsService.retrieveAllFriends(userId);
        return new ResponseEntity<>(response, OK);
    }

    @DeleteMapping("/friends/remove")
    public ResponseEntity<String> removeFriend() {
        LOGGER.debug("Hit DELETE /api/v1/user/friends/remove endpoint");
        String response = "Friend Removed.";
        return new ResponseEntity<>(response, OK);
    }

}
