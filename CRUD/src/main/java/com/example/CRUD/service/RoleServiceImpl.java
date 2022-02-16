package com.example.CRUD.service;

import com.example.CRUD.models.Role;
import com.example.CRUD.userrepository.RolesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    private RolesRepository rolesRepository;

    public List<Role> getAllRoles() {
        return (List<Role>) rolesRepository.findAll();
    }

    public RoleServiceImpl(RolesRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    public Role findById(Long id){
        return rolesRepository.findById(id).orElse(null);
    }
    @Override
    public  void saveRole(Role role){
        rolesRepository.save(role);
    }
}
