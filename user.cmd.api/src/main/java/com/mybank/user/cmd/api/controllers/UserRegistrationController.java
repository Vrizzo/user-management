package com.mybank.user.cmd.api.controllers;


import com.mybank.user.cmd.api.commands.RegisterUserCommand;
import com.mybank.user.cmd.api.dto.BaseResponse;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserRegistrationController {

    private final CommandGateway commandGateway;

    public UserRegistrationController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public ResponseEntity<RegisterUserResponse> registerUser(@RequestBody RegisterUserCommand command) {
        String id = UUID.randomUUID().toString();
        command.setId(id);
        try {
            commandGateway.sendAndWait(command);
            return ResponseEntity.ok(new RegisterUserResponse("User registered successfully", command.getId()));
        } catch (Exception e) {
            var safeErrorMessage = "Error while processing register user request for id - " + id;
            System.out.println(e.toString());

            return new ResponseEntity<>(new RegisterUserResponse(id, safeErrorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private class RegisterUserResponse extends BaseResponse {
        private final String id;

        public RegisterUserResponse(String message, String id) {
            super(message);
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }
}
