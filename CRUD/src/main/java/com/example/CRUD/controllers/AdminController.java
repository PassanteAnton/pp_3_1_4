package com.example.CRUD.controllers;

import com.example.CRUD.models.Role;
import com.example.CRUD.models.User;
import com.example.CRUD.service.RoleService;
import com.example.CRUD.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;

    // https://html5css.ru/bootstrap4/bootstrap_tables.php
    //https://getbootstrap.su/docs/5.0/utilities/background/?

    @GetMapping()
    public String getAll(Model model, Authentication authentication) {
        model.addAttribute("currenUser", (User)authentication.getPrincipal());
        model.addAttribute("users", userService.getAllUser());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/adminWithJS";

    }

    @GetMapping("/new")
    public String addNewUserFromAdmin(Model model,Authentication authentication){
        model.addAttribute("userForm", new User());
        model.addAttribute("currenUser", (User)authentication.getPrincipal());
        model.addAttribute("roles", roleService.getAllRoles());
        return "admin/adminNewFom";

    }


    @PatchMapping("/edit")
    public String updateUser(User user){
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@ModelAttribute("user") User user){
        userService.deleteUser(user.getId());
        return "redirect:/admin";
    }


    @GetMapping("/gt/{userId}")
    public String gtUser(@PathVariable("userId") Long userId, Model model){
        model.addAttribute("users", userService.userGtList(userId));
        return "admin/adminNewFom";
    }

    @PostMapping("/addNew")
    private String addUser(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model,Authentication authentication) {
        // Что бы не переделовать ДБ использовал поле PasswordConfirm для ввода роли.

        if (!userService.saveUser(userForm)) {
            model.addAttribute("currenUser", authentication.getPrincipal());
            model.addAttribute("userNameError", "Такой пользователь уже существует");
            System.out.println("user neme error");
            return "admin/adminNewFom";
        }
        return "redirect:/admin";
    }

    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
}
