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

import static dev.andrewjfei.user.management.api.example.enums.Error.FRIENDSHIP_NOT_FOUND;
import static dev.andrewjfei.user.management.api.example.enums.Error.USER_FRIEND_REQUEST_ERROR;
import static dev.andrewjfei.user.management.api.example.enums.Error.USER_FRIEND_REQUEST_NOT_PENDING_ERROR;
import static dev.andrewjfei.user.management.api.example.enums.Error.USER_NOT_FOUND;
import static dev.andrewjfei.user.management.api.example.utils.MapperUtil.toBasicUserResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class UserFriendsService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserFriendsService.class);

    private final String USER_NOT_FOUND_LOGGER_STRING = "User ({}) does not exist";

    private final String FRIENDSHIP_NOT_FOUND_LOGGER_STRING =
            "Friendship with requester [User] ({}) and receiver [User] ({}) does not exist";

    private final String FRIEND_REQUEST_NOT_PENDING_LOGGER_STRING =
            "Friendship with requester [User] ({}) and receiver [User] ({}) is not pending";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendshipRepository friendshipRepository;

    public UserFriendsService() {

    }

    /********************* User Friends Methods *********************/

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

        FriendshipDao newFriendshipDao = createFriendshipDao(requesterId, receiverId);

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

    /********************* User Friend Requests Methods *********************/

    public void acceptFriendRequest(UUID receiverId, UUID requesterId) {
        FriendshipDao friendshipDao = friendshipRepository.findByRequesterIdAndReceiverId(requesterId, receiverId);

        if (friendshipDao == null) {
            // TODO: Throw Exception (Friendship Not Found)
            LOGGER.error(FRIENDSHIP_NOT_FOUND_LOGGER_STRING, requesterId, receiverId);
            throw new UserManagementApiExampleException(FRIENDSHIP_NOT_FOUND, BAD_REQUEST);
        }

        if (friendshipDao.isAccepted()) {
            // TODO: Throw Exception (Already Accepted Friend Request)
            LOGGER.error(FRIEND_REQUEST_NOT_PENDING_LOGGER_STRING, requesterId, receiverId);
            throw new UserManagementApiExampleException(USER_FRIEND_REQUEST_NOT_PENDING_ERROR, BAD_REQUEST);
        }

        // Accept friendship and create reverse friendship record to ensure bidirectional relation
        friendshipDao.setAccepted(true);

        FriendshipDao reverseFriendshipDao = createFriendshipDao(receiverId, requesterId);
        reverseFriendshipDao.setAccepted(true);

        friendshipRepository.save(friendshipDao);
        friendshipRepository.save(reverseFriendshipDao);
    }

    public void declineFriendRequest(UUID receiverId, UUID requesterId) {
        FriendshipDao friendshipDao = friendshipRepository.findByRequesterIdAndReceiverId(requesterId, receiverId);

        if (friendshipDao == null) {
            // TODO: Throw Exception (Friendship Not Found)
            LOGGER.error(FRIENDSHIP_NOT_FOUND_LOGGER_STRING, requesterId, receiverId);
            throw new UserManagementApiExampleException(FRIENDSHIP_NOT_FOUND, BAD_REQUEST);
        }

        if (friendshipDao.isAccepted()) {
            // TODO: Throw Exception (Already Accepted Friend Request)
            LOGGER.error(FRIEND_REQUEST_NOT_PENDING_LOGGER_STRING, requesterId, receiverId);
            throw new UserManagementApiExampleException(USER_FRIEND_REQUEST_NOT_PENDING_ERROR, BAD_REQUEST);
        }

        // Remove friendship record from database
        friendshipRepository.deleteById(friendshipDao.getId());
    }

    public List<BasicUserResponse> retrieveAllFriendRequests(UUID userId) {
        List<FriendshipDao> friendshipDaoList = friendshipRepository
                .findByReceiverIdAndIsAccepted(userId, false);

        List<BasicUserResponse> response = new ArrayList<>();

        for (FriendshipDao friendshipDao : friendshipDaoList) {
            response.add(toBasicUserResponse(friendshipDao.getRequester()));
        }

        return response;
    }

    /********************* Helper Methods *********************/

    public FriendshipDao createFriendshipDao(UUID requesterId, UUID receiverId) {
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

        return new FriendshipDao(requesterDao, receiverDao);
    }

}
