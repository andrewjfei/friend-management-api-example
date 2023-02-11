package dev.andrewjfei.user.management.api.example.services.v1;

import dev.andrewjfei.user.management.api.example.daos.FriendshipDao;
import dev.andrewjfei.user.management.api.example.daos.UserDao;
import dev.andrewjfei.user.management.api.example.exceptions.UserManagementApiExampleException;
import dev.andrewjfei.user.management.api.example.repositories.v1.UserRepository;
import dev.andrewjfei.user.management.api.example.transactions.responses.BasicUserResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static dev.andrewjfei.user.management.api.example.enums.Error.USER_NOT_FOUND;
import static dev.andrewjfei.user.management.api.example.utils.MapperUtil.toBasicUserResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class UserFriendsService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserFriendsService.class);

    @Autowired
    private UserRepository userRepository;

    public UserFriendsService() {

    }

    public List<BasicUserResponse> retrieveAllFriends(UUID userId) {
        Optional<UserDao> userDaoOptional = userRepository.findById(userId);

        if (userDaoOptional.isEmpty()) {
            LOGGER.error("User ({}) does not exist", userId);
            throw new UserManagementApiExampleException(USER_NOT_FOUND, BAD_REQUEST);
        }

        UserDao userDao = userDaoOptional.get();

        List<BasicUserResponse> response = new ArrayList<>();

        for (FriendshipDao friendshipDao : userDao.getFriends()) {
            UUID friendId = friendshipDao.getReceiver().getId();

            Optional<UserDao> friendDaoOptional = userRepository.findById(friendId);

            if (friendDaoOptional.isEmpty()) {
                LOGGER.error("User ({}) does not exist", friendId);
                throw new UserManagementApiExampleException(USER_NOT_FOUND, BAD_REQUEST);
            }

            UserDao friendDao = friendDaoOptional.get();

            response.add(toBasicUserResponse(friendDao));
        }

        return response;
    }

}
