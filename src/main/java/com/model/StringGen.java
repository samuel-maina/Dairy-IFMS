/*
 * This file is written by and is property of Samuel Maina Mwangi.
 */
package com.model;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * Random String key generator
 * 
 * StringGen.java
 * 
 * @author Samuel Maina
 * 
 * 10-23-2021
 * 
 * @version 1.0
 * 
 */
public class StringGen {

    /**
     * generates a random string with 9 characters
     *
     * @return RandomString
     */
    public static String createString() {
        return RandomStringUtils.random(9, true, true).toUpperCase();
    }

}
