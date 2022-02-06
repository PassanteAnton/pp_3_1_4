package com.example.CRUD.userrepository;

import com.example.CRUD.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRepository extends JpaRepository<Role, Long> {
}
