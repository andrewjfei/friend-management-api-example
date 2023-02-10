package dev.andrewjfei.user.management.api.example.controllers.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    /********************* User APIs *********************/

    @PatchMapping("/block")
    public ResponseEntity<String> blockUser() {
        logger.debug("Hit PATCH /api/v1/user/block endpoint");
        String response = "User blocked.";
        return new ResponseEntity<>(response, OK);
    }

    /********************* User Friends APIs *********************/

    @PostMapping("/friends/add")
    public ResponseEntity<String> addFriend() {
        logger.debug("Hit POST /api/v1/user/friends/add endpoint");
        String response = "Friend request sent.";
        return new ResponseEntity<>(response, OK);
    }

    @GetMapping("/friends")
    public ResponseEntity<String> fetchAllFriends() {
        logger.debug("Hit GET /api/v1/user/friends endpoint");
        String response = "Fetched all friends.";
        return new ResponseEntity<>(response, OK);
    }

    @DeleteMapping("/friends/remove")
    public ResponseEntity<String> removeFriend() {
        logger.debug("Hit DELETE /api/v1/user/friends/remove endpoint");
        String response = "Friend Removed.";
        return new ResponseEntity<>(response, OK);
    }

}
