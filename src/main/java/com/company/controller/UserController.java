package com.company.controller;

import com.company.model.exceptions.GeneralAPIException;
import com.company.model.web.UserRequest;
import com.company.model.web.UserResponse;
import com.company.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public UserResponse create(@RequestBody UserRequest userRequest) throws GeneralAPIException {
        return userService.create(userRequest);
    }

    @PutMapping("/update/{userId}")
    public UserResponse update(@RequestBody UserRequest userRequest, @PathVariable("userId") Integer userId) throws GeneralAPIException {
        return userService.update(userRequest);
    }

    @GetMapping("/all")
    public List<UserResponse> getAll() throws GeneralAPIException {
        return userService.getAll();
    }

    @GetMapping("/search")
    public UserResponse getByUsername(@RequestParam("username") String username) throws GeneralAPIException {
        return userService.getByUsername(username);
    }

    @DeleteMapping("/delete/{userId}")
    public void deleteByUserId(@PathVariable("userId") Integer userId) throws GeneralAPIException {
        userService.deleteByUserId(userId);
    }
}