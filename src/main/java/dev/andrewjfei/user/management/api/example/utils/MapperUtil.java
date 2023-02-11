package dev.andrewjfei.user.management.api.example.utils;

import dev.andrewjfei.user.management.api.example.daos.UserDao;
import dev.andrewjfei.user.management.api.example.enums.Error;
import dev.andrewjfei.user.management.api.example.transactions.responses.BasicUserResponse;
import dev.andrewjfei.user.management.api.example.transactions.responses.ErrorResponse;

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

    public static ErrorResponse toErrorResponse(Error error) {
        return new ErrorResponse(
                error.getErrorCode(),
                error.getMessage()
        );
    }

}
