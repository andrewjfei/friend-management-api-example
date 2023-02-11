package dev.andrewjfei.user.management.api.example.utils;

import dev.andrewjfei.user.management.api.example.daos.FriendshipDao;
import dev.andrewjfei.user.management.api.example.daos.UserDao;
import org.jeasy.random.EasyRandom;

public class FriendshipUtil {

    private static final EasyRandom easyRandom = new EasyRandom();

    private FriendshipUtil() {

    }

    public static FriendshipDao generateRandomFriendshipDao() {
        return new FriendshipDao(
                easyRandom.nextObject(UserDao.class),
                easyRandom.nextObject(UserDao.class)
        );
    }

}
