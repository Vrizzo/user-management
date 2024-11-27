package com.mybank.user.query.api.dto;

import com.mybank.user.core.models.User;

import java.util.List;

public class UserLookupResponse {
    private final List<User> users;

    public UserLookupResponse(List<User> users) {
        this.users = users;
    }

    public UserLookupResponse(User user) {
        this.users = List.of(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
