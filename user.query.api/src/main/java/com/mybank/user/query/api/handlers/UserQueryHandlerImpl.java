package com.mybank.user.query.api.handlers;

import com.mybank.user.core.models.User;
import com.mybank.user.query.api.dto.UserLookupResponse;
import com.mybank.user.query.api.queries.FindAllUsersQuery;
import com.mybank.user.query.api.queries.FindUserByIdQuery;
import com.mybank.user.query.api.queries.SearchUserQuery;
import com.mybank.user.query.api.repository.UserRepository;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserQueryHandlerImpl implements UserQueryHandler {

    private final UserRepository userRepository;

    public UserQueryHandlerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryHandler
    @Override
    public UserLookupResponse findUserById(FindUserByIdQuery query) {
        Optional<User> byId = userRepository.findById(query.getId());
        return new UserLookupResponse(byId.orElse(null));

    }

    @QueryHandler
    @Override
    public UserLookupResponse searchUser(SearchUserQuery query) {
        List<User> users = userRepository.findByFilter(query.getFilter());
        return new UserLookupResponse(users);
    }

    @QueryHandler
    @Override
    public UserLookupResponse getAllUsers(FindAllUsersQuery query) {
        List<User> users = userRepository.findAll();
        return new UserLookupResponse(users);
    }
}
