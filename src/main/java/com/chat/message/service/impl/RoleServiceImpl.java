package com.chat.message.service.impl;

import com.chat.message.model.Role;
import com.chat.message.repository.RoleRepository;
import com.chat.message.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<Role> findByName(String roleName) {
        return roleRepository.findByName(roleName);
    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
