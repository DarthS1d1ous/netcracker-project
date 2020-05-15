package com.netcracker.edu.backend.controller;

import com.netcracker.edu.backend.entity.User;
import com.netcracker.edu.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<User> getByUsername(@RequestParam(value = "username") String username) {
        Optional<User> user = userService.findByUsername(username);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable(name = "id") long id) {
        userService.deleteUser(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        Optional<User> user = userService.findById(id);
        return user.isPresent() ? ResponseEntity.ok(user.get()) : ResponseEntity.notFound().build();
    }

    @RequestMapping(value = "/username/{username}", method = RequestMethod.GET)
    public String getUsernameIfExists(@PathVariable(name = "username") String username) {
        return this.userService.findUsernameIfExists(username);
    }

    @RequestMapping(value = "/{userId}/subscription/{subscriptionId}", method = RequestMethod.DELETE)
    public void deleteSubscription(@PathVariable(name = "subscriptionId") long subscriptionId, @PathVariable(name = "userId") long userId) {
        userService.deleteSubscription(subscriptionId, userId);
    }

    @RequestMapping(value = "/{userId}/subscription/{subscriptionId}", method = RequestMethod.POST)
    public void insertSubscription(@PathVariable(name = "subscriptionId") long subscriptionId, @PathVariable(name = "userId") long userId) {
        userService.insertSubscription(subscriptionId, userId);
    }


    @RequestMapping(value = "/likes/count/{id}", method = RequestMethod.GET)
    public Integer findUserLikesCount(@PathVariable(name = "id") long id) {
        return userService.findUserLikesCount(id);
    }
}
