package com.mybank.user.cmd.api.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.io.Serializable;

public class RemoveUserCommand implements Serializable
{
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @TargetAggregateIdentifier
    private String id;
}
