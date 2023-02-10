package dev.andrewjfei.user.management.api.example.controllers.v1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/add")
    public ResponseEntity<String> sendFriendRequest() {
        logger.debug("Hit POST /api/v1/user/add endpoint");
        String response = "Friend request sent.";
        return new ResponseEntity<>(response, OK);
    }

    @PatchMapping("/block")
    public ResponseEntity<String> blockUser() {
        logger.debug("Hit PATCH /api/v1/user/block endpoint");
        String response = "User blocked.";
        return new ResponseEntity<>(response, OK);
    }

}
