package com.netcracker.edu.backend.service.impl;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.repository.UserRepository;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteSubscription(long subscriptionId, long userId) {
        userRepository.deleteSubscription(subscriptionId, userId);
    }

    @Override
    public void insertSubscription(long subscriptionId, long userId) {
        long sd = subscriptionId;
        userRepository.insertSubscription(subscriptionId, userId);
    }

    @Override
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User findPostLikeByUserId(long postId, long userId) {
        return userRepository.findPostLikeByUserId(postId, userId);
    }

    @Override
    public String findUsernameIfExists(String username) {
        return userRepository.findUsernameIfExists(username);
    }

    @Override
    public Integer findUserLikesCount(long id) {
        return userRepository.findUserLikesCount(id);
    }
}

