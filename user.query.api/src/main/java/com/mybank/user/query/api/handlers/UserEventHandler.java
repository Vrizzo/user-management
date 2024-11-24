package com.mybank.user.query.api.handlers;


import com.mybank.user.core.events.UserRegisterEvent;
import com.mybank.user.core.events.UserRemoveEvent;
import com.mybank.user.core.events.UserUpdateEvent;

public interface UserEventHandler {
    public void on(UserRemoveEvent event);
    public void on(UserUpdateEvent event);
    public void on(UserRegisterEvent event);
}

