package com.model;

import java.util.Date;

/**
 * Defines an exception object that carries the exception back in a HTTP
 * RESPONSE
 *
 * @author Samuel Maina
 */
public class ExceptionObject {

    private String message; // information about the exception
    private Date date; // time object when the exception happened

    /**
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     *
     * @return date
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ExceptionObject{" + "message=" + message + ", date=" + date + '}';
    }

}
