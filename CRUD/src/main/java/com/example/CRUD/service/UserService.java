package com.example.CRUD.service;

import com.example.CRUD.models.User;

import java.util.List;

public interface UserService {
    boolean saveUser(User user);
    List<User> getAllUser();
    User findById(Long id);
    boolean updateUser( User user);
    boolean deleteUser(Long ID);
    List<User> userGtList(Long idMin);
}
