package com.example.CRUD.controllers;

import com.example.CRUD.models.User;
import com.example.CRUD.service.UserService;
import com.example.CRUD.service.UserServiceImpl;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("/")
public class UserController {
    private UserService userService;


    @GetMapping()
    public String getUserById() {
        return "users/welcome";
    }

    @GetMapping("/user")
    public String welcomeUser(Authentication authentication, Model model){
        model.addAttribute("user", authentication.getPrincipal());
        return "users/welcomeUser";
    }
    @GetMapping("edit/{id}")
    public String editUserForm(Model model, @PathVariable("id") long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        if(Long.compare(id, user.getId()) != 0){
            return "users/wrong";
        }else {
            model.addAttribute("user", userService.findById(id));
        }
        return "users/edit";
    }
    @PatchMapping("user/{id}")
    public String editUser(@ModelAttribute ("user") User user, Authentication authentication){

        User userAuthentication = (User) authentication.getPrincipal();
        if(Long.compare(userAuthentication.getId(), user.getId()) != 0){
            return "users/wrong";
        }else {
            userService.updateUser(user);
            return "redirect:/user";
        }
    }

    @DeleteMapping("user/{id}")
    public String deleteUser(@PathVariable("id") Long id, Authentication authentication){
        User user = (User) authentication.getPrincipal();

        if(Long.compare(id, user.getId()) != 0){
            return "users/wrong";
        }else {
            userService.deleteUser(id);
            return "redirect:/";
        }

    }

    public UserController(UserService userService) {
        this.userService = userService;
    }
}

