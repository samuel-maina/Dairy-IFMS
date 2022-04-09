package com.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * class defines a Deductions object A deduction is money cut from the members
 * account monthly
 *
 * @author Samuel Maina 
 * 
 * Deductions.java 
 * 
 * 12/01/2022
 * 
 * @version 1.0
 */
@Entity
@Table(name = "deduction_t")
public class Deductions implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private int amount; // Amont to be deducted from each member
    @NotNull
    private Date date; // Date when a deduction is created
    @NotNull
    private String description; // explains what the deduction is about

    public int getAmount() {
        return amount;
    }

    /**
     *
     * @param amount total amount in shillings each cooperative member is
     * deducted
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     *
     * @return date when a deduction was set
     */
    public Date getDate() {
        return date;
    }

    /**
     *
     * @param date when a new deduction is created
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     *
     * @return description of the deduction
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description of the deduction
     */

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return id of the deduction
     */
    public long getmId() {
        return id;
    }

}
