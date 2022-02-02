package com.example.CRUD.controllers;

import com.example.CRUD.models.User;
import com.example.CRUD.service.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class CRUDController {
    private UserServiceImpl userService;

    @GetMapping()
    public String getAll(ModelMap model){
        model.addAttribute("users",userService.getAllUser());
        return "users/AllUsers";
    }

    @GetMapping("/{id}")
    public String getUserById(Model model, @PathVariable("id") long id){
        model.addAttribute("user", userService.getUser(id));
        return "users/oneById";
    }
    @GetMapping("/new")
    public String newUserForm(Model model){
        model.addAttribute("user", new User());
        return "users/newForm";
    }
    @GetMapping("/{id}/edit")
    public String editUserForm(Model model, @PathVariable("id") long id){
        model.addAttribute("user", userService.getUser(id));
        return "users/edit";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/users";
    }
    @PatchMapping("/{id}")
    public String editUser(@ModelAttribute("user") User user){
        userService.updateUser(user);
        return "redirect:/users";
    }
    @DeleteMapping("/{id}")
    public String deleteUser(@ModelAttribute("user") User user){
        userService.deleteUser(user);
        return "redirect:/users";
    }

    public CRUDController(UserServiceImpl userService) {
        this.userService = userService;
    }
}

