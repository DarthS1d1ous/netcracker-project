package com.netcracker.edu.fapi.controller;

import com.netcracker.edu.fapi.exception.UserRegistrationException;
import com.netcracker.edu.fapi.models.User;
import com.netcracker.edu.fapi.service.UserService;
import com.netcracker.edu.fapi.validator.UserRegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRegistrationValidator userRegistrationValidator;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/user")
    public User getUserByUsername(@RequestParam(value = "username") String username) {
        return userService.findByUsername(username);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/current")
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userService.findByUsername(((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername());
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable(name = "id") long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/{userId}/subscription/{subscriptionId}" ,method = RequestMethod.DELETE)
    public void deleteSubscription(@PathVariable(name = "subscriptionId") long subscriptionId,@PathVariable(name = "userId") long userId){
        userService.deleteSubscription(subscriptionId, userId);
    }

    @RequestMapping(value = "/{userId}/subscription/{subscriptionId}" ,method = RequestMethod.POST)
    public void insertSubscription(@PathVariable(name = "subscriptionId") long subscriptionId,@PathVariable(name = "userId") long userId){
        userService.insertSubscription(subscriptionId, userId);
    }

    @GetMapping(value = "/likes/count/{id}")
    public Integer findUserLikesCount(@PathVariable(name = "id") long id){
        return userService.findUserLikesCount(id);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = "application/json")
    public User saveUser(@Valid @RequestBody User user, BindingResult result) {
        if(user.getId()==0L) {
            userRegistrationValidator.validate(user, result);
            if (result.hasErrors()) {
                throw new UserRegistrationException(result.getAllErrors().get(0).getCode());
            }
        }
        return userService.saveUser(user);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable(name = "id") long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/username/{username}")
    public String getUsernameIfExists(@PathVariable(name = "username") String username) {
        return userService.getUsernameIfExists(username);
    }

}
