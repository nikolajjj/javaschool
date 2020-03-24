package com.tsystems.service.implementation;

import com.tsystems.Util.HashPasswordUtil;
import com.tsystems.dao.api.UserDAO;
import com.tsystems.dto.UserDTO;
import com.tsystems.entity.Converter.Converter;
import com.tsystems.entity.User;
import com.tsystems.entity.enums.Role;
import com.tsystems.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        user.setRole(Role.USER);
        user.setPassword(HashPasswordUtil.getHash(user.getPassword()));
        userDAO.add(user);
    }

    @Override
    @Transactional
    public UserDTO findUserById(Integer id) {
        return Converter.getUserDto(userDAO.findById(id));
    }

    @Override
    public UserDTO findUserByUsername(String username) {
        return Converter.getUserDto(userDAO.findByUsername(username));
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDAO.update(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userDAO.delete(user);
    }

    @Override
    @Transactional
    public List<UserDTO> getAllUsers() {
        return Converter.getUserDtos(userDAO.getAll());
    }
}
