package com.example.CRUD.config;

import com.example.CRUD.models.Role;
import com.example.CRUD.models.User;
import com.example.CRUD.service.RoleService;
import com.example.CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RunAfterStartup {
    UserService userService;

    RoleService roleService;

    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() {
        roleService.saveRole(new Role(1L, "ROLE_USER"));
        roleService.saveRole(new Role(2L, "ROLE_ADMIN"));
        System.out.println(roleService.getAllRoles());
        User firstAdmin = new User();
        firstAdmin.setName("Name");
        firstAdmin.setLastName("LastName");
        firstAdmin.setAge((byte) 22);
        firstAdmin.setEmail("Email@Mail.ru");
        firstAdmin.setUserName("1");
        firstAdmin.setPassword("1");
        firstAdmin.setPasswordConfirm("ADMIN");
        userService.saveUser(firstAdmin);

        System.out.println("Админ аккаунт User name:1 Password:1");
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
}
