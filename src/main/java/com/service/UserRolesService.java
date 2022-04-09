package com.service;

import com.exceptions.ResourceNotFoundException;
import com.model.Role;
import com.model.User;
import com.model.UserRole;
import com.repository.UserRolesRepository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samuel
 */
@Service
public class UserRolesService {

    @Autowired
    private UserRolesRepository userRolesRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    /**
     * finds all roles and assinged users
     *
     * @return
     */
    public Iterable<UserRole> roles() {
        return userRolesRepository.findAll();
    }

    /**
     * Saves a user role
     *
     * @param userAndRole map object holding userid and roleId
     * @return saved user roles
     */
    public Iterable<UserRole> save(Map<String, String> userAndRole) {
        List<UserRole> userRole_ = new ArrayList<>();
        Set set = userAndRole.entrySet();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            Map.Entry map = (Map.Entry) it.next();
            String userId = String.valueOf(map.getKey());
            String roleId = String.valueOf(map.getValue());
            User user = userService.findUserById(userId);
            Role role = roleService.findById(roleId);
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRoles(role);
            userRole_.add(userRole);
        }
        return userRolesRepository.saveAll(userRole_);
    }

    /**
     * find a user-role by id
     *
     * @param userRoleId unique user role identifier
     * @return userRole object
     */
    public UserRole findById(long userRoleId) {
        Optional<UserRole> userRole = userRolesRepository.findById(userRoleId);
        return userRole.orElseThrow(() -> new ResourceNotFoundException(""));
    }

    /**
     * retrieves all roles assigned to a given user
     *
     * @param userId unique user identifier
     * @return list of all roles associated with a given user
     */
    public List<UserRole> getRoleByUserId(String userId) {
        return userRolesRepository.findUserRolesByUserId(userId);
    }

    /**
     * deletes a userRole object by ID
     *
     * @param userRoleId unique userRole identifier
     */
    public void delete(long userRoleId) {
        UserRole userRole = findById(userRoleId);
        userRolesRepository.deleteUserRole(userRole.getId());

    }

    /**
     * deletes all roles associated with a user
     *
     * @param userId unique user identifier
     */
    public void deleteAllRolesByUserId(String userId) {
        userRolesRepository.deleteRolesByUserId(userId);
    }

}
