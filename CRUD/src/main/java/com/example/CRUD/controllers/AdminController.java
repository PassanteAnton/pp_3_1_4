package com.example.CRUD.controllers;

import com.example.CRUD.models.User;
import com.example.CRUD.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;


    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("users", userService.getAllUser());
        return "admin/admin";

    }
    @GetMapping("/{id}/edit")
    public String userDateEditOrDelete(@PathVariable("id")Long id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "admin/adminEdit";
    }
    @GetMapping("/new")
    public String addNewUserFromAdmin(Model model){
        model.addAttribute("user", new User());
        return "admin/admiNew";

    }
    @PostMapping()
    public String createUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PatchMapping("/{id}")
    public String updateUser(@PathVariable(required = true)Long id, User user){
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
        return "admin/admin";
    }


    public AdminController(UserService userService) {
        this.userService = userService;
    }
}
