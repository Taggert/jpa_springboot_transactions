package com.company.repository;

import com.company.model.User;
import com.company.model.UserInfo;

import java.util.List;

public interface UserInfoRepository {

    UserInfo save(UserInfo userInfo);
    UserInfo update(UserInfo userInfo);
    List<UserInfo> findAll();
    UserInfo findById(Integer userInfoId);
    UserInfo findByUser(User user);
    void deleteByUser(User user);


}