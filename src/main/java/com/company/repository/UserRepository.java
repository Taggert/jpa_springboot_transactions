package com.company.repository;

import com.company.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);
    User update(User user);
    User findById(Integer userId);
    List findAll();
    User findByUsername(String username);


}