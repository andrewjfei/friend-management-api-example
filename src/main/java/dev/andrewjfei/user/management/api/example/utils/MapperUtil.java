package dev.andrewjfei.user.management.api.example.utils;

import dev.andrewjfei.user.management.api.example.daos.UserDao;
import dev.andrewjfei.user.management.api.example.transactions.responses.BasicUserResponse;

public class MapperUtil {

    private MapperUtil() {

    }

    public static BasicUserResponse toBasicUserResponse(UserDao userDao) {
        return new BasicUserResponse(
                userDao.getUsername(),
                userDao.getFirstName(),
                userDao.getLastName()
        );
    }
}
