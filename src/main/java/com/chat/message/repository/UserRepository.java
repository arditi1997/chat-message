package com.chat.message.repository;

import com.chat.message.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
   Optional<User> findByUserNameAndEmail(String username, String email);
   Optional<User> findByUserName(String username);
}
