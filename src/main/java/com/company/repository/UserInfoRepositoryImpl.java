package com.company.repository;

import com.company.model.User;
import com.company.model.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public UserInfo save(UserInfo userInfo) {
        entityManager.persist(userInfo);
        return userInfo;
    }

    @Override
    public UserInfo update(UserInfo userInfo) {
        entityManager.merge(userInfo);
        return userInfo;
    }

    @Override
    public List<UserInfo> findAll() {
        return entityManager.createQuery("from UserInfo").getResultList();
    }

    @Override
    public UserInfo findById(Integer userInfoId) {
        return entityManager.find(UserInfo.class, userInfoId);

    }

    @Override
    public UserInfo findByUser(User user) {
        String jpql = "from UserInfo ui where ui.user.id = :userId";
        List userInfos = entityManager.createQuery(jpql).setParameter("userId", user.getId()).getResultList();
        if(!userInfos.isEmpty()){
            return (UserInfo) userInfos.get(0);
        }
        return null;
    }

    @Override
    public void deleteByUser(User user) {
        entityManager.remove(findByUser(user));
    }
}