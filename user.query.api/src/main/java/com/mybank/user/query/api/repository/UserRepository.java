package com.mybank.user.query.api.repository;

import com.mybank.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository  extends MongoRepository<User, String> {
}
