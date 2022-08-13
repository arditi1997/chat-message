package com.chat.message.service;

import com.chat.message.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(String roleName);
    void save (Role role);
}
