package com.controller;

import com.service.UserRolesService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for path user-roles
 * 
 * UserRolesController.java
 * 
 * @author Samuel Maina
 * 
 * @version 1.0
 * 
 * 03/10/2022
 * 
 */
@RestController
@RequestMapping("/api/v1/user-roles")
public class UserRolesController {

    @Autowired
    private UserRolesService userRolesService;

    /**
     * retrieves roles and users
     *
     * @param userId unique user identifier
     * @return roles assigned to users
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> get(@PathVariable String userId) {
        return new ResponseEntity<>(userRolesService.getRoleByUserId(userId), HttpStatus.OK);
    }

    /**
     * Saves user roles
     *
     * @param userRole map object
     * @return map of saved user roles
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Map<String, String> userRole) {
        return new ResponseEntity<>(userRolesService.save(userRole), HttpStatus.OK);
    }

    /**
     * deletes a role-user assignment
     *
     * @param userRoleId unique user role identifier
     * @return HttpStatus
     */
    @DeleteMapping("/{userRoleId}")
    public ResponseEntity<?> delete(@PathVariable("userRoleId") long userRoleId) {
        userRolesService.delete(userRoleId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * gets all user roles
     *
     * @return list of user roles
     */
    @GetMapping
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(userRolesService.roles(), HttpStatus.OK);
    }

    /**
     * deletes all roles assigned to a user.
     *
     * @param userId unique user identifier
     * @return HttpStats
     */
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<?> deleteAllByUser(@PathVariable String userId) {
        userRolesService.deleteAllRolesByUserId(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
