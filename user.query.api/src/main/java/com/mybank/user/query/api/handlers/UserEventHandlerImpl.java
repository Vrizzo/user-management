package com.mybank.user.query.api.handlers;

import com.mybank.user.core.events.UserRegisteredEvent;
import com.mybank.user.core.events.UserRemoveEvent;
import com.mybank.user.core.events.UserUpdateEvent;
import com.mybank.user.query.api.repository.UserRepository;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@ProcessingGroup("user-group")
public class UserEventHandlerImpl implements UserEventHandler {

    private final UserRepository userRepository;

    public UserEventHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @EventHandler
    @Override
    public void on(UserRegisteredEvent event) {
        System.out.println("UserEventHandlerImpl.on(UserRegisterEvent event)");
        userRepository.save(event.getUser());
    }
    @EventHandler
    @Override
    public void on(UserUpdateEvent event) {
        System.out.println("UserEventHandlerImpl.on(UserUpdateEvent event)");
        userRepository.save(event.getUser());
    }
    @EventHandler
    @Override
    public void on(UserRemoveEvent event) {
        System.out.println("UserEventHandlerImpl.on(UserRemoveEvent event)");
        userRepository.deleteById(event.getId());
    }
}
