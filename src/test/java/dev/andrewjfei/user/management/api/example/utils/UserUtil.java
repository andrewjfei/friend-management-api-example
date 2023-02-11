package dev.andrewjfei.user.management.api.example.utils;

import dev.andrewjfei.user.management.api.example.daos.UserDao;
import org.jeasy.random.EasyRandom;

public class UserUtil {

    private static final EasyRandom easyRandom = new EasyRandom();

    private UserUtil() {

    }

    public static UserDao generateRandomUserDao() {
        return new UserDao(
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class)
        );
    }

}
