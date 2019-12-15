package com.netcracker.edu.fapi.service;

import com.netcracker.edu.fapi.models.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();

    User findByUsername(String username);

    User saveUser(User User);

    void deleteUser(long id);

    User findById(long id);

    String getUsernameIfExists(String username);

    Integer findUserLikesCount(long id);

}
