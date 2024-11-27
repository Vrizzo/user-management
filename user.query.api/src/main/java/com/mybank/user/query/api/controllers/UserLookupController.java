package com.mybank.user.query.api.controllers;

import com.mybank.user.query.api.dto.UserLookupResponse;
import com.mybank.user.query.api.queries.FindAllUsersQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/userLookup")
public class UserLookupController {
    private final QueryGateway queryGateway;

    public UserLookupController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public ResponseEntity<UserLookupResponse> getAllUsers() {
        try {
            return ResponseEntity.ok(queryGateway.query("findAllUsers", new FindAllUsersQuery(), UserLookupResponse.class).join());
        }catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(500).build();
        }
    }
}
