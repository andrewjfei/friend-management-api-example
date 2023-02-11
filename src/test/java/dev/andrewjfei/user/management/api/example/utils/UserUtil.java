package dev.andrewjfei.user.management.api.example.utils;

import dev.andrewjfei.user.management.api.example.daos.UserDAO;
import org.jeasy.random.EasyRandom;

public class UserUtil {

    private static final EasyRandom easyRandom = new EasyRandom();

    private UserUtil() {

    }

    public static UserDAO generateRandomUserDAO() {
        return new UserDAO(
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class),
                easyRandom.nextObject(String.class)
        );
    }
}
