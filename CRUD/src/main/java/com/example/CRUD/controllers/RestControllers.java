package com.example.CRUD.controllers;


import com.example.CRUD.models.User;
import com.example.CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/rest")
public class RestControllers {
    UserService userService;

    @GetMapping("/{id}")
    public List<User> getUser(@PathVariable("id") Long id){

        User user =  userService.findById(id);

        return Collections.singletonList(user);
    }
    @GetMapping()
    public List<User> getAll(Authentication authentication){
        User currentUser = (User) authentication.getPrincipal();
        List<User> users = userService.getAllUser();
        users.add(0, currentUser);
        return users;
    }
    @PostMapping("/{id}")
    public ResponseEntity<?> editUser(@PathVariable Long id,
                                      @RequestBody User user){
        if(userService.updateUser(user)) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        if(userService.deleteUser(id))return new ResponseEntity<>(HttpStatus.OK);
        else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}