package com.mybank.user.query.api.controllers;

import com.mybank.user.query.api.dto.UserLookupResponse;
import com.mybank.user.query.api.queries.FindAllUsersQuery;
import com.mybank.user.query.api.queries.FindUserByIdQuery;
import com.mybank.user.query.api.queries.SearchUserQuery;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            return ResponseEntity.ok(queryGateway.query( new FindAllUsersQuery(), UserLookupResponse.class).join());
        }catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    @RequestMapping(path="/{id}")
    public ResponseEntity<UserLookupResponse> getByID(@PathVariable(value = "id") String id) {
        try {
            return ResponseEntity.ok(queryGateway.query( new FindUserByIdQuery(id), UserLookupResponse.class).join());
        }catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping
    @RequestMapping(path="/byfilter/{filter}")
    public ResponseEntity<UserLookupResponse> getByFilter(@PathVariable(value = "filter") String filter) {
        try {
            return ResponseEntity.ok(queryGateway.query( new SearchUserQuery(filter), UserLookupResponse.class).join());
        }catch (Exception e) {
            System.out.println(e.toString());
            return ResponseEntity.status(500).build();
        }
    }
}
