package com.mybank.user.core.events;

import java.io.Serializable;

public class UserRemoveEvent implements Serializable {
    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
