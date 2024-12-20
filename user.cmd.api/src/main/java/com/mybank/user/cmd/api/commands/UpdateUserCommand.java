package com.mybank.user.cmd.api.commands;

import com.mybank.user.core.models.User;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

public class UpdateUserCommand implements Serializable
{

    @TargetAggregateIdentifier
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
