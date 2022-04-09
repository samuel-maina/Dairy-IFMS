/*
 * This file is written by and is property of Samuel Maina Mwangi.
 */
package com.exceptions;

/**
 * Exception is generated when a sale is confirmed outside the 2 minutes period
 * 
 * StaleSaleException.java
 * 
 * @author Samuel Maina
 *
 * 02-22-2022
 *
 * @version 1.0
 */
public class StaleSaleException extends RuntimeException {

    public StaleSaleException(String sale_already_confirmed) {
        super(sale_already_confirmed);
    }

}
