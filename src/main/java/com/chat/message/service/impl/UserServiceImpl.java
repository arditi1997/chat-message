package com.chat.message.service.impl;

import com.chat.message.exception.CustomException;
import com.chat.message.exception.ErrorCode;
import com.chat.message.model.Role;
import com.chat.message.model.RoleEnum;
import com.chat.message.model.User;
import com.chat.message.repository.RoleRepository;
import com.chat.message.repository.UserRepository;
import com.chat.message.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void registerUser(User user) {
        Optional<User> userFound = userRepository.findByUserNameAndEmail(user.getUserName(), user.getEmail());
        if(!userFound.isEmpty())
            throw new CustomException(ErrorCode.USER_ALREADY_REGISTERED, ErrorCode.USER_ALREADY_REGISTERED.getMessage());
        Optional<Role> role = roleRepository.findByName(RoleEnum.USER.getValue());
        user.setRole(role.get());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
       return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username).orElseThrow(() ->
                new CustomException(ErrorCode.USER_NOT_FOUND, ErrorCode.USER_NOT_FOUND.getMessage()));
    }
}
