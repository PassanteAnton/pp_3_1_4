package com.example.CRUD.userrepository;

import com.example.CRUD.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomUserRepository extends CrudRepository <User, Long>{
    User findByUserName(String userName);

}
