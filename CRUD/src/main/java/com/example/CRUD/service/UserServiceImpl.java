package com.example.CRUD.service;

import com.example.CRUD.models.User;
import com.example.CRUD.userrepository.CustomUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{

        @Autowired
        private CustomUserRepository userDao;

        @Override
        @Transactional
        public void saveUser(User user) {
            userDao.save(user);
        }

        @Override
        public List<User> getAllUser() {
            return (List<User>) userDao.findAll();
        }

        @Override
        public User getUser(Long id) {
            return userDao.findById(id).orElse(null);
        }

        @Override
        @Transactional
        public void updateUser(User user) {
            userDao.save(user);
        }

        @Override
        @Transactional
        public void deleteUser(User user) {
            userDao.delete(user);
        }
}
