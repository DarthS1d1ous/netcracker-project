package com.netcracker.edu.backend.service;

import com.netcracker.edu.backend.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> findById(long id);
    List<User> findAllUsers();
    Optional<User> findByUsername(String username);
    User saveUser(User User);
    void deleteUser(long id);
    User findPostLikeByUserId(long postId, long userId);
    String findUsernameIfExists(String username);
    Integer findUserLikesCount(long id);
    void deleteSubscription(long subscriptionId, long userId);
    void insertSubscription(long subscriptionId, long userId);

}
