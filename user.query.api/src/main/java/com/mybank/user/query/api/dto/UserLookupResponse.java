package com.mybank.user.query.api.dto;

import com.mybank.user.core.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserLookupResponse {
    private final List<User> users;

    public UserLookupResponse(List<User> users) {
        this.users = users;
    }

    public UserLookupResponse(User user) {
        List<User> users = new ArrayList<User>();
        users.add(user);
        this.users = users;
    }

    public List<User> getUsers() {
        return users;
    }
}
