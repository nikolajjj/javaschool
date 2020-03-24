package com.tsystems.dao.implementation;

import com.tsystems.entity.User;
import com.tsystems.dao.api.UserDAO;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {
    @Override
    public User findByUsername(String username) {
        Query findByUsername = entityManager.createQuery("select user from User user where user.username=:username");
        findByUsername.setParameter("username", username);
        if(findByUsername.getResultList().size() > 0) {
            return (User) findByUsername.getResultList().get(0);
        } else {
            return null;
        }
    }
}
