package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 * @author samuel
 */
@Entity
@Table(name = "memberPurchases_t")
public class MemberPurchases implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //unique purchase identifier

    @JsonIgnore
    @ManyToOne
    private Member member; // Member object/instrance

    @JsonIgnore
    @ManyToOne
    private User user; // User object/instance

    private PurchaseType type; // vet or store

    private int amount; // price of purchases

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date; // date purchase is done

    private boolean confirmed; // is purchase confirmed

    private String confirmationCode; // unique purchase identifier similar to purchase ID

    /**
     *
     * @return confirmationCode
     */
    public String getConfirmationCode() {
        return confirmationCode;
    }

    /**
     *
     * @param confirmationCode
     */
    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }

    /**
     *
     * @return member instance
     */
    public Member getMember() {
        return member;
    }

    /**
     *
     * @param member Member instance
     */
    public void setMember(Member member) {
        this.member = member;
    }

    /**
     *
     * @return purchaseType
     */
    public PurchaseType getType() {
        return type;
    }

    /**
     *
     * @param type
     */
    public void setType(PurchaseType type) {
        this.type = type;
    }

    /**
     *
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
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

    /**
     *
     * @return confirmed
     */
    public boolean isConfirmed() {
        return confirmed;
    }

    /**
     *
     * @param confirmed
     */
    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
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

    /**
     *
     * @return userId
     */
    public String getUserId() {
        return user.getUserId();
    }

    /**
     *
     * @return memberId
     */
    public long getMemberId() {
        return member.getId();
    }

}
