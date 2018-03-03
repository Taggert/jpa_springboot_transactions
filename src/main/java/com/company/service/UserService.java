package com.company.service;

import com.company.model.exceptions.GeneralAPIException;
import com.company.model.web.UserRequest;
import com.company.model.web.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse create(UserRequest userRequest) throws GeneralAPIException;

    UserResponse update(UserRequest userRequest) throws GeneralAPIException;

    List<UserResponse> getAll() throws GeneralAPIException;

    UserResponse getByUsername(String username) throws GeneralAPIException;

    void deleteByUserId(Integer userId) throws GeneralAPIException;

    void saveError(Exception e);


}