package com.service;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The purpose of this class is to activate and deactivate user accounts bases
 * on time set.
 *
 * @author Samuel Maina 03/10/2022
 * @version 1.0
 */
@Service
public class Activator extends TimerTask {

    private UserService userService = new UserService();

    @Override
    public void run() {
        userService.scheduledAccountActivation();
    }

    /**
     * sets activator intervals. checks once per minute on all accounts
     */
    public static void startTimer() {
        Timer timer = new Timer();
        Date date = new Date();
        Activator activator = new Activator();
        timer.schedule(activator, date, 45000);

    }

}
