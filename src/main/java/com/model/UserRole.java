package com.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Defines user and role relation/assignment
 * 
 * UserRole.java
 * 
 * @author Samuel Maina
 * 
 * 12-10-2021
 * 
 * @version 1.0
 */
@Entity
@Table(name = "user_roles_t")
public class UserRole implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; // unique user-role assignment identifier
    //@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User user; // user object/instance

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Role roles; // role object/instance

    /**
     *
     * @return userId
     */
    public String getUser() {
        return user.getUserId();
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     * @return roleTypes
     */
    public Roles getUserRole() {
        return roles.getRoleType();

    }

    /**
     *
     * @param roles
     */
    public void setRoles(Role roles) {
        this.roles = roles;
    }

    /**
     *
     * @return id
     */
    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "UserRole" + "id=" + id + ", user=" + user.getUserId();
    }

}
