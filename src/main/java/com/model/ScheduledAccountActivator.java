/*
 */
package com.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Defines a scheduler object for auto-activation and auto-deactivation
 * 
 * SceduledAccountActivator.java
 * 
 * @author samuel
 * 
 * 12-11-2021
 * 
 * @version 1.0
 */
@Entity
@Table(name = "scheduled_account_activator")
public class ScheduledAccountActivator implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //unique identifier

    @Column(name = "activation_time")
    private String activationTime; // time when a user account is auto-enabled

    @Column(name = "deactivation_time")
    private String deactivationTime; //time when a user account is auto-disabled

    @OneToOne
    private User user; // user object/instance

    /**
     *
     * @return activationTime
     */
    public String getActivationTime() {
        return activationTime;
    }

    /**
     *
     * @param activationTime
     */
    public void setActivationTime(String activationTime) {
        this.activationTime = activationTime;
    }

    /**
     *
     * @return deactivationTime
     */
    public String getDeactivationTime() {
        return deactivationTime;
    }

    /**
     *
     * @param deactivationTime
     */
    public void setDeactivationTime(String deactivationTime) {
        this.deactivationTime = deactivationTime;
    }

    /**
     *
     * @return user
     */
    public User getUser() {
        return user;
    }

    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ScheduledAccountActivator{" + "id=" + id + ", activationTime=" + activationTime + ", deactivationTime=" + deactivationTime + ", user=" + user + '}';
    }

}
