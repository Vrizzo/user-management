package com.mybank.user.query.api.queries;

public class FindUserByIdQuery {
    private final String id;

    public FindUserByIdQuery(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
