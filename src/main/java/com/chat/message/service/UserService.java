package com.chat.message.service;

import com.chat.message.model.User;

import java.util.List;

public interface UserService {
    void registerUser(User user);
    List<User> getUsers();
    User findByUsername(String username);
}
