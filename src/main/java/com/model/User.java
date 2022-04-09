package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Defines a system user
 * 
 * User.java
 * 
 * @author Samuel Maina
 * 
 * 09-10-2021
 * 
 * @version 1.0
 */
@Entity
@Table(name = "users")
public class User implements Serializable {

    @Column(name = "firstname")
    private String firstName; // user's first name

    @Column(name = "secondname")
    private String secondName; // user's second name
    @Id
    @Column(unique = true)
    private String id; //unique user identifier

    @Column(name = "national_Id")
    private String nationalId; //user's nationa ID

    private String password; // ser's password

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<UserRole> userRoles; //list of userRoles assigned to te user

    @Column(name = "enabled")
    private boolean enabled;  // is account enabled 
    @Column(name = "locked")
    private boolean locked; // is account locked

    @JsonIgnore
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<MemberPurchases> purchases;
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<MilkRecord> milkRecords;
    @JsonIgnore
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private ScheduledAccountActivator scheduledAccountActivator;

    /**
     *
     * @return firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     *
     * @return secondName
     */
    public String getSecondName() {
        return secondName;
    }

    /**
     *
     * @param secondName
     */
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     *
     * @return id
     */
    public String getUserId() {
        return id;
    }

    /**
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.id = userId;
    }

    /**
     *
     * @return nationalId
     */
    public String getNationalId() {
        return nationalId;
    }

    /**
     *
     * @param nationalId
     */
    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    /**
     *
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return userRoles
     */
    public List<UserRole> getUserRoles() {

        return userRoles;
    }

    /**
     *
     * @param userRoles
     */
    public void setUserRoles(List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    /**
     *
     * @return enabled
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     *
     * @param enabled
     */
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**
     *
     * @return purchases
     */
    public List<MemberPurchases> getPurchases() {
        return purchases;
    }

    /**
     *
     * @param purchases
     */
    public void setPurchases(List<MemberPurchases> purchases) {
        this.purchases = purchases;
    }

    /**
     *
     * @return locked
     */
    public boolean isLocked() {

        return locked;
    }

    /**
     *
     * @param locked
     */
    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    /**
     *
     * @return isSchedled
     */
    public boolean isScheduled() {
        return (scheduledAccountActivator != null);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", userRoles=" + userRoles + ", enabled=" + enabled + ", purchases=" + purchases + '}';
    }

}
