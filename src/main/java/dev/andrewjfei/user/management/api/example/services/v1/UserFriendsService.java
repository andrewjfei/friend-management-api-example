package dev.andrewjfei.user.management.api.example.services.v1;

import dev.andrewjfei.user.management.api.example.daos.FriendshipDao;
import dev.andrewjfei.user.management.api.example.daos.UserDao;
import dev.andrewjfei.user.management.api.example.exceptions.UserManagementApiExampleException;
import dev.andrewjfei.user.management.api.example.repositories.v1.FriendshipRepository;
import dev.andrewjfei.user.management.api.example.repositories.v1.UserRepository;
import dev.andrewjfei.user.management.api.example.transactions.responses.BasicUserResponse;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static dev.andrewjfei.user.management.api.example.enums.Error.USER_FRIEND_REQUEST_ERROR;
import static dev.andrewjfei.user.management.api.example.enums.Error.USER_NOT_FOUND;
import static dev.andrewjfei.user.management.api.example.utils.MapperUtil.toBasicUserResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class UserFriendsService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserFriendsService.class);

    private final String USER_NOT_FOUND_LOGGER_STRING = "User ({}) does not exist";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    public UserFriendsService() {

    }

    public void sendFriendRequest(UUID requesterId, UUID receiverId) {
        FriendshipDao friendshipDao = friendshipRepository.findByRequesterIdAndReceiverId(requesterId, receiverId);

        if (friendshipDao != null) {
            LOGGER.info(
                    "User ({}) has either already sent a friend request to User ({}) or are already friends",
                    requesterId,
                    receiverId
            );
            throw new UserManagementApiExampleException(USER_FRIEND_REQUEST_ERROR, BAD_REQUEST);
        }

        Optional<UserDao> requesterDaoOptional = userRepository.findById(requesterId);
        Optional<UserDao> receiverDaoOptional = userRepository.findById(receiverId);

        UUID invalidUserId = null;

        if (requesterDaoOptional.isEmpty()) {
            invalidUserId = requesterId;
        } else if (receiverDaoOptional.isEmpty()) {
            invalidUserId = receiverId;
        }

        if (invalidUserId != null) {
            LOGGER.error(USER_NOT_FOUND_LOGGER_STRING, invalidUserId);
            throw new UserManagementApiExampleException(USER_NOT_FOUND, BAD_REQUEST);
        }

        UserDao requesterDao = requesterDaoOptional.get();
        UserDao receiverDao = receiverDaoOptional.get();

        FriendshipDao newFriendshipDao = new FriendshipDao(requesterDao, receiverDao);

        friendshipRepository.save(newFriendshipDao);
    }

    @Transactional
    public List<BasicUserResponse> retrieveAllFriends(UUID userId) {
        Optional<UserDao> userDaoOptional = userRepository.findById(userId);

        if (userDaoOptional.isEmpty()) {
            LOGGER.error(USER_NOT_FOUND_LOGGER_STRING, userId);
            throw new UserManagementApiExampleException(USER_NOT_FOUND, BAD_REQUEST);
        }

        UserDao userDao = userDaoOptional.get();

        List<BasicUserResponse> response = new ArrayList<>();

        for (FriendshipDao friendshipDao : userDao.getFriends()) {
            UUID friendId = friendshipDao.getReceiver().getId();

            Optional<UserDao> friendDaoOptional = userRepository.findById(friendId);

            if (friendDaoOptional.isEmpty()) {
                LOGGER.error(USER_NOT_FOUND_LOGGER_STRING, friendId);
                throw new UserManagementApiExampleException(USER_NOT_FOUND, BAD_REQUEST);
            }

            UserDao friendDao = friendDaoOptional.get();

            response.add(toBasicUserResponse(friendDao));
        }

        return response;
    }

}
