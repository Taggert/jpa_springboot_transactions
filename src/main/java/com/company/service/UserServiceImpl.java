package com.company.service;

import com.company.model.ErrorReport;
import com.company.model.User;
import com.company.model.UserInfo;
import com.company.model.exceptions.GeneralAPIException;
import com.company.model.web.UserRequest;
import com.company.model.web.UserResponse;
import com.company.repository.ErrorRepository;
import com.company.repository.UserInfoRepository;
import com.company.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private ErrorRepository errorRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = GeneralAPIException.class)
    public UserResponse create(UserRequest userRequest) throws GeneralAPIException {
        try {
            User user = new User();
            user = user.builder()
                    .username(userRequest.getUsername())
                    .build();

            userRepository.save(user);


            if (1 == 1) {
                throw new GeneralAPIException("Create exception");
            }
            UserInfo userInfo = new UserInfo();
            userInfo = userInfo.builder()
                    .user(user)
                    .firstname(userRequest.getFirstname())
                    .lastname(userRequest.getLastname())
                    .build();
            userInfoRepository.save(userInfo);

            return UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .firstname(userInfo.getFirstname())
                    .lastname(userInfo.getLastname())
                    .build();
        }catch (Exception e){
            userService.saveError(e);
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public UserResponse update(UserRequest userRequest) throws GeneralAPIException {
        try {

            User user = userRepository.findByUsername(userRequest.getUsername());
            UserInfo userInfo = userInfoRepository.findByUser(user);
            user.setUsername(userRequest.getUsername());
            userRepository.update(user);
            if (1 == 1) {
                throw new GeneralAPIException("Update exception");

            }
            userInfo.setUser(user);
            userInfo.setFirstname(userRequest.getFirstname());
            userInfo.setLastname(userRequest.getLastname());
            userInfoRepository.update(userInfo);

            return UserResponse.builder()
                    .id(user.getId())
                    .username(user.getUsername())
                    .firstname(userInfo.getFirstname())
                    .lastname(userInfo.getLastname())
                    .build();
        }catch (Exception e){
            userService.saveError(e);
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public List<UserResponse> getAll() throws GeneralAPIException {
        try {
            List<UserInfo> userInfos = userInfoRepository.findAll();
            List<UserResponse> userResponses = new ArrayList<>();
            for (UserInfo userInfo : userInfos) {
                userResponses.add(UserResponse.builder()
                        .id(userInfo.getUser().getId())
                        .username(userInfo.getUser().getUsername())
                        .firstname(userInfo.getFirstname())
                        .lastname(userInfo.getLastname())
                        .build());
            }
            if (1 == 1) {
                GeneralAPIException generalAPIException = new GeneralAPIException("GetAll exception");
                userService.saveError(generalAPIException);
                throw generalAPIException;

            }
            return userResponses;
        }catch (Exception e){
            userService.saveError(e);
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public UserResponse getByUsername(String username) throws GeneralAPIException {
        try {
            User byUsername = userRepository.findByUsername(username);
            if (byUsername == null) {
                GeneralAPIException generalAPIException = new GeneralAPIException("No such user");
                saveError(generalAPIException);
                throw generalAPIException;
            }
            UserInfo userInfo = userInfoRepository.findByUser(byUsername);
            if (1 == 1) {
                GeneralAPIException generalAPIException = new GeneralAPIException("GetByUsername exception");
                userService.saveError(generalAPIException);
                throw generalAPIException;

            }
            return UserResponse.builder()
                    .id(userInfo.getUser().getId())
                    .username(userInfo.getUser().getUsername())
                    .firstname(userInfo.getFirstname())
                    .lastname(userInfo.getLastname())
                    .build();
        }catch (Exception e){
            userService.saveError(e);
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    @Transactional
    public void deleteByUserId(Integer userId) throws GeneralAPIException {
        try {
            userInfoRepository.deleteByUser(userRepository.findById(userId));
            if (1 == 1) {
                GeneralAPIException generalAPIException = new GeneralAPIException("Delete exception");
                userService.saveError(generalAPIException);
                throw generalAPIException;

            }
        }catch (Exception e){
            userService.saveError(e);
            System.out.println(e.getMessage());
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void saveError(Exception e) {
        ErrorReport errorReport = new ErrorReport();
        errorReport = errorReport.builder()
                .exceptionType(e.getClass().getCanonicalName())
                .message(e.getMessage())
                .build();
        errorRepository.save(errorReport);
    }

}