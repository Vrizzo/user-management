package com.mybank.user.core.events;

import com.mybank.user.core.models.User;

import java.io.Serializable;

public class UserRegisteredEvent implements Serializable {
    private String id;

    private User user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
