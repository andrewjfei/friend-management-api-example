package dev.andrewjfei.user.management.api.example.services.v1;

import dev.andrewjfei.user.management.api.example.daos.FriendshipDao;
import dev.andrewjfei.user.management.api.example.daos.UserDao;
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

import static dev.andrewjfei.user.management.api.example.utils.MapperUtil.toBasicUserResponse;

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
            // TODO: Throw user not found exception
            LOGGER.error("User not found error");
            throw new RuntimeException();
        }

        UserDao userDao = userDaoOptional.get();

        List<BasicUserResponse> response = new ArrayList<>();

        for (FriendshipDao friendshipDao : userDao.getFriends()) {
            Optional<UserDao> friendDaoOptional = userRepository.findById(friendshipDao.getReceiver().getId());

            if (friendDaoOptional.isEmpty()) {
                // TODO: Throw user not found exception
                LOGGER.error("User not found error");
                throw new RuntimeException();
            }

            UserDao friendDao = friendDaoOptional.get();

            response.add(toBasicUserResponse(friendDao));
        }

        return response;
    }

}
