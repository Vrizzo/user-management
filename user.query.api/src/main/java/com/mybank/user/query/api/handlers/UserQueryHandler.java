package com.mybank.user.query.api.handlers;

import com.mybank.user.core.models.User;
import com.mybank.user.query.api.dto.UserLookupResponse;
import com.mybank.user.query.api.queries.FindAllUsersQuery;
import com.mybank.user.query.api.queries.FindUserByIdQuery;
import com.mybank.user.query.api.queries.SearchUserQuery;

public interface UserQueryHandler {
    UserLookupResponse findUserById(FindUserByIdQuery query);
    UserLookupResponse searchUser(SearchUserQuery query);
    UserLookupResponse getAllUsers(FindAllUsersQuery query);
}
