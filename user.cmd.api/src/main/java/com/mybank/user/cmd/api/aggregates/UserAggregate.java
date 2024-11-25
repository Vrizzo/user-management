package com.mybank.user.cmd.api.aggregates;

import com.mybank.user.cmd.api.commands.RegisterUserCommand;
import com.mybank.user.cmd.api.commands.RemoveUserCommand;
import com.mybank.user.cmd.api.commands.UpdateUserCommand;
import com.mybank.user.cmd.api.security.PasswordEncoder;
import com.mybank.user.cmd.api.security.PasswordEncoderService;
import com.mybank.user.core.events.UserRegisterEvent;
import com.mybank.user.core.events.UserRemoveEvent;
import com.mybank.user.core.events.UserUpdateEvent;
import com.mybank.user.core.models.User;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;


@Aggregate
public class UserAggregate {

    private final PasswordEncoder passwordEncoder;
    @AggregateIdentifier
    private String id;
    private User user;

    @Autowired
    @CommandHandler
    public UserAggregate(RegisterUserCommand command) {
        System.out.println("Handling RegisterUserCommand");
        this.passwordEncoder = new PasswordEncoderService();
        User newUser = command.getUser();
        newUser.setId(id);
        String password = newUser.getAccount().getPassword();
        newUser.getAccount().setPassword(passwordEncoder.hashPassword(password));

        UserRegisterEvent event = new UserRegisterEvent();
        event.setId(command.getId());
        event.setUser(newUser);

        AggregateLifecycle.apply(event);
    }

    public UserAggregate() {
        System.out.println("UserAggregate created UserAggregate()");
        this.passwordEncoder = new PasswordEncoderService();
    }

    @CommandHandler
    public void handle(UpdateUserCommand command) {
        User updatedUser = command.getUser();
        updatedUser.setId(id);
        String password = updatedUser.getAccount().getPassword();
        updatedUser.getAccount().setPassword(passwordEncoder.hashPassword(password));

        UserUpdateEvent event = new UserUpdateEvent();
        event.setId(UUID.randomUUID().toString());
        event.setUser(updatedUser);

        AggregateLifecycle.apply(event);
    }

    @CommandHandler
    public void handle(RemoveUserCommand command) {
        UserRemoveEvent event = new UserRemoveEvent();
        event.setId(command.getId());
        AggregateLifecycle.apply(event);
    }

    @EventSourcingHandler
    public void on(RegisterUserCommand event) {
        this.id = event.getId();
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(UpdateUserCommand event) {
        this.user = event.getUser();
    }

    @EventSourcingHandler
    public void on(RemoveUserCommand event) {
        AggregateLifecycle.markDeleted();
    }

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
