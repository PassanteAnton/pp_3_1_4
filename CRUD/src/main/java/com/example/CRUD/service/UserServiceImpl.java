package com.example.CRUD.service;

import com.example.CRUD.models.Role;
import com.example.CRUD.models.User;
import com.example.CRUD.userrepository.CustomUserRepository;
import com.example.CRUD.userrepository.RolesRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService,UserDetailsService{

    @PersistenceContext
    private EntityManager em;
    RolesRepository roleRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    private CustomUserRepository userRepository;


        public boolean saveUser(User user) {
            User userFromDb = userRepository.findByUserName(user.getUserName());

            if(userFromDb != null){

                return false;
            }
           Set<Role>roles = new HashSet<>();
            roles.add(new Role(1L, "ROLE_USER"));
            user.setRoles(roles);
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return true;
        }

        public List<User> getAllUser() {
            return (List<User>) userRepository.findAll();
        }


        public User findById(Long id) {
            return userRepository.findById(id).orElse(new User());
        }



        public boolean updateUser(User user) {
            User fromDb = userRepository.findById(user.getId()).orElse(null);
            if ( fromDb != null) {
                fromDb.setName(user.getName());
                fromDb.setLastName(user.getLastName());
                fromDb.setAge(user.getAge());
                fromDb.setEmail(user.getEmail());
                userRepository.save(fromDb);
                return true;
            }
            return false;
        }


        public boolean deleteUser(Long id) {
            if(userRepository.findById(id).isPresent()) {
                userRepository.deleteById(id);
                return true;
            }
            return false;
        }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
    public List<User> userGtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }

    public UserServiceImpl(EntityManager em, RolesRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder, CustomUserRepository userRepository) {
        this.em = em;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
    }
}
