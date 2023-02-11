package dev.andrewjfei.user.management.api.example.models;

import dev.andrewjfei.user.management.api.example.daos.UserDao;
import lombok.Getter;

@Getter
public class FriendshipId {

    private UserDao requester;

    private UserDao receiver;

    public FriendshipId() {

    }

    public FriendshipId(UserDao requester, UserDao receiver) {
        this.requester = requester;
        this.receiver = receiver;
    }

}
