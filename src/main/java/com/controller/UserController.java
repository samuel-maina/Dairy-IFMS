/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller;

import com.model.ScheduledAccountActivator;
import com.model.User;
import com.service.UserRolesService;
import com.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for users
 * 
 * UserController.java
 * 
 * 03-10-2022
 * 
 * @author Samuel Maina
 * 
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserRolesService userRoleService;

    /**
     * finds all user data
     *
     * @return all user data
     */
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    /**
     * find user roles
     *
     * @return users and assigned roles
     */
    @GetMapping("/roles")
    public ResponseEntity<?> getUserRoles() {
        return new ResponseEntity<>(userRoleService.roles(), HttpStatus.OK);
    }

    /**
     * Creates a new user
     *
     * @param user user object
     * @return created user
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    /**
     * Schedules account to be active at a give time
     *
     * @param scheduledAccountActivator time object start and end
     * @param userId unique user identifier
     * @return scheduler
     */
    @PostMapping("/schedule/{userId}")
    public ResponseEntity<?> scheduleUserAccount(@RequestBody ScheduledAccountActivator scheduledAccountActivator, @PathVariable String userId) {
        return new ResponseEntity<>(userService.scheduleAccount(scheduledAccountActivator, userId), HttpStatus.OK);
    }

    /**
     * Updates a user object
     *
     * @param user user object
     * @param userId unique user Identifier
     * @return updated user object
     */
    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@RequestBody User user, @PathVariable String userId) {
        return new ResponseEntity<>(userService.update(user, userId), HttpStatus.OK);
    }

    /**
     * retrieves user by Id
     *
     * @param userId unique user identifier
     * @return updated user object
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> loadUser(@PathVariable String userId) {
        return new ResponseEntity<>(userService.findUserById(userId), HttpStatus.OK);
    }

}
