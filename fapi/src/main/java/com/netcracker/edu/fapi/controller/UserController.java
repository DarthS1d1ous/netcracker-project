package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/")
    public User getUserByUsername(@RequestParam(value = "username") String username) {
        return userService.findByUsername(username);
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable(name = "id") long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public User saveUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteUser(id);
    }

}
