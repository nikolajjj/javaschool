package com.tsystems.service.api;

import com.tsystems.dto.UserDTO;
import com.tsystems.entity.User;

import java.util.List;

/**
 *
 */
public interface UserService {
    /**
     *
     */
    void addUser(User user);

    /**
     *
     * @param id
     * @return
     */
    UserDTO findUserById(Integer id);

    /**
     *
     * @param user
     */
    void updateUser(User user);

    /**
     *
     * @param username
     * @return
     */
    UserDTO findUserByUsername(String username);

    /**
     *
     * @param user
     */
    void deleteUser(User user);

    /**
     *
     * @return
     */
    List<UserDTO> getAllUsers();
}
