package com.service;

import static com.Application.logger;
import com.exceptions.ResourceNotFoundException;
import com.model.ScheduledAccountActivator;
import com.model.User;
import com.repository.ScheduledAccountActivatorRepository;
import com.repository.UserRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * class handles the auto-activation and auto-deactivation of user accounts
 * based on time set
 *
 * 03-10-2022
 *
 * @author Samuel Maina
 * @version 1.0
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduledAccountActivatorRepository scheduledAccountActivatorRepository;

    /**
     * Saves users
     *
     * @param user User object/instance
     * @return scheduled user
     */
    public User saveUser(User user) {
        logger.info("Registering new user " + user);
        return userRepository.save(user);
    }

    /**
     * retrieves all users
     *
     * @return list of users
     */
    public Iterable getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * finds user by id
     *
     * @param userId
     * @return user object
     */
    public User findUserById(String userId) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResourceNotFoundException("user not found");
        }
    }

    /**
     * sets the activation and deactivation status of an account
     */
    public void scheduledAccountActivation() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Africa/Nairobi"));
        DateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        Date date = cal.getTime();
        String t = dateFormat.format(date);
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from User M where M.scheduledAccountActivator.deactivationTime =:time");
        query.setParameter("time", t);
        Query query_ = session.createQuery("from User M where M.scheduledAccountActivator.activationTime=:time");
        query_.setParameter("time", t);
        List<User> users = query.getResultList();
        for (User user : users) {
            user.setEnabled(false);
            session.save(user);
        }
        List<User> users_ = query_.getResultList();
        for (User user : users_) {
            user.setEnabled(true);
            session.save(user);
        }
        transaction.commit();

    }

    /**
     * schedules an account
     *
     * @param scheduledAccountActivator time object [activation and deactivation
     * time]
     * @param userId unique user identifier
     * @return ScheduledAccountActivator object
     */
    public ScheduledAccountActivator scheduleAccount(ScheduledAccountActivator scheduledAccountActivator, String userId) {
        User user = this.findUserById(userId);
        scheduledAccountActivator.setUser(user);
        return scheduledAccountActivatorRepository.save(scheduledAccountActivator);
    }

    /**
     * update a user object
     *
     * @param user object
     * @param userId unique identifier
     * @return updated user object
     */
    public User update(User user, String userId) {
        User user_ = findUserById(userId);
        user_.setFirstName(user.getFirstName());
        user_.setNationalId(user.getNationalId());
        user_.setSecondName(user.getSecondName());
        return userRepository.save(user_);
    }

}
