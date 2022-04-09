package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Defines the properties of a member
 * Member.java
 *
 * @author Samuel Maina 02/02/2022
 * @version 1.0
 */
@Entity
@Table(name = "members_t")
public class Member implements Serializable {

    @Id
    private long id; // member registration number

    private String name; //member's names

    private String nationalId; // member's national ID

    private String phoneNumber; // members first phone number

    private String altPhoneNumber; // member alternative phone number

    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<MilkRecord> milkRecord;
    
    @JsonIgnore
    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<MemberPurchases> purchases;

    /**
     *
     * @return member registration number
     */
    public long getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
     * @return phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     *
     * @return altPhoneNumber
     */
    public String getAltPhoneNumber() {
        return altPhoneNumber;
    }

    /**
     *
     * @param altPhoneNumber
     */
    public void setAltPhoneNumber(String altPhoneNumber) {
        this.altPhoneNumber = altPhoneNumber;
    }

    /**
     *
     * @return milkRecord
     */
    public List<MilkRecord> getMilkRecord() {
        return milkRecord;
    }

    /**
     *
     * @param milkRecord
     */
    public void setMilkRecord(List<MilkRecord> milkRecord) {
        this.milkRecord = milkRecord;
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

    @Override
    public String toString() {
        return "Member{" + "id=" + id + '}';
    }

}
