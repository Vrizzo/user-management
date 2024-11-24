package com.mybank.user.cmd.api.commands;

import com.mybank.user.core.models.User;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class RemoveUserCommand
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
