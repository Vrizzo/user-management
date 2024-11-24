package com.mybank.user.core.events;

import com.mybank.user.core.models.User;

public class UserRemoveEvent {
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
