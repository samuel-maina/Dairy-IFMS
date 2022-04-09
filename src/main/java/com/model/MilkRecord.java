package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * class defines a milkRecord object
 * 
 * MilkRecord.java
 * 
 * @author Samuel Maina
 *
 * 02-22-2022
 *
 * @version 1.0
 */
@Entity
@Table(name = "milkRecord_t")
public class MilkRecord implements Serializable {

    @Id
    private String transactionId; //unique transaction identifier

    private double amount; // amount in litres

    private LocalDate date; // date the transaction is made

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private Member member; //member object/instance
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User user; //user object/instance

    private String entryGroup; // number of 45 ltr milk container

    /**
     *
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     *
     * @return date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     *
     * @return entryGroup
     */
    public String getEntryGroup() {
        return entryGroup;
    }

    /**
     *
     * @param entryGroup
     */
    public void setEntryGroup(String entryGroup) {
        this.entryGroup = entryGroup;
    }

    /**
     *
     * @return member
     */
    @JsonIgnore
    public Member getMember() {
        return member;
    }

    /**
     *
     * @param member
     */
    @JsonIgnore
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     *
     * @return memberId
     */
    public long getMemberNo() {
        return member.getId();
    }

    /**
     *
     * @return transactionId
     */
    public String getTransactionId() {
        return transactionId;
    }

    /**
     *
     * @param transactionId
     */
    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    /**
     *
     * @return userId
     */
    public String getUserId() {
        return user.getUserId();
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
        return "MilkRecord{" + "transactionID=" + transactionId + ", amount=" + amount + ", date=" + date + ", member=" + member + ", user=" + user.getUserId() + ", entryGroup=" + entryGroup + '}';
    }

}
