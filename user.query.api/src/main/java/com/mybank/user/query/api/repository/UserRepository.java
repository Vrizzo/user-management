package com.mybank.user.query.api.repository;

import com.mybank.user.core.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {
    @Query("{ '$or' : [ { 'firstName' : { $regex: ?0, $options: 'i' } }, { 'lastName' : { $regex: ?0, $options: 'i' } } , { 'emailAddress' : { $regex: ?0, $options: 'i' } }] }")
    List<User> findByFilter(String filter);
}
