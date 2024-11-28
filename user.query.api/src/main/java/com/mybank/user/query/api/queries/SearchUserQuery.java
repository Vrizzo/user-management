package com.mybank.user.query.api.queries;

public class SearchUserQuery {
    private final String filter;

    public SearchUserQuery(String filter) {
        this.filter = filter;
    }

    public String getFilter() {
        return filter;
    }
}
