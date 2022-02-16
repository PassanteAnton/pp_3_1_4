package com.example.CRUD.service;

import com.example.CRUD.models.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role findById(Long id);
    void saveRole(Role role);
}
