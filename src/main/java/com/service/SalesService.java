/*
 * This file is written by and is property of Samuel Maina Mwangi.
 */
package com.service;

import com.exceptions.ResourceNotFoundException;
import com.exceptions.StaleSaleException;
import com.model.Member;
import com.model.MemberPurchases;
import com.model.StringGen;
import com.model.User;
import com.repository.MemberPurchasesRepository;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * service class for all store and vet purchases- goods and services
 * @author Samuel Maina
 * 2022-02-01
 * @Version 1
 */
@Service
public class SalesService {
    @Autowired
    private MemberPurchasesRepository memberPurchasesRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DeductionsService deductionsService;

    @Autowired
    private MilkService milkService;

    /**
     * save a new member purchase
     *
     * @param purchases
     * @param id
     * @param authentication
     * @return an unconfirmed memberPurchase
     */
    public MemberPurchases save(MemberPurchases purchases, long id, Authentication authentication) {
        User user = userService.findUserById(authentication.getName());
        Member member = memberService.findById(id);
        purchases.setConfirmed(false);
        purchases.setUser(user);
        purchases.setMember(member);
        purchases.setDate(new Date());
        purchases.setConfirmationCode(StringGen.createString());
        return memberPurchasesRepository.save(purchases);
    }

    /**
     * Confirms a memberPurchase using a unique Purchases Id. For purchase to be
     * confirmed it must be no older than two minutes
     *
     * @param id
     * @param code
     * @return a confirmed memberPurchase
     */
    public MemberPurchases confirmSale(long id, String code) {
        MemberPurchases memberPurchases = memberPurchasesRepository.unconfirmedPurchase(code, id);
        if (memberPurchases == null) {
            throw new ResourceNotFoundException("not_found");
        }
        if ((new Date().getTime() - memberPurchases.getDate().getTime()) / 60000 > 5) // if time difference is greater than two minutes, abort.
        {
            throw new StaleSaleException("Purchase is stale, older than five minutes");
        }
        memberPurchases.setConfirmed(true);
        return memberPurchasesRepository.save(memberPurchases);
    }

    /**
     *
     * find the sum of all store service purchases by a given member using year
     * and month
     *
     * @param year
     * @param month
     * @param id
     * @return
     */
    public int memberStoreTotalsByYearAndMonth(int year, int month, long id) {

        Integer totals = memberPurchasesRepository.memberStorePurchaseTotalsByYearAndMonth(year, month, id);
        if (totals == null) {
            return 0;
        } else {
            return (int) totals;
        }
    }

    /**
     * find the sum of all vet service purchases by a given member using year
     * and month
     *
     * @param year
     * @param month
     * @param id
     * @return sum
     */
    public int memberVetTotalsByYearAndMonth(int year, int month, long id) {

        Integer totals = memberPurchasesRepository.memberVetPurchaseTotalsByYearAndMonth(year, month, id);
        if (totals == null) {
            return 0;
        } else {
            return (int) totals;
        }
    }
/**
 * 
 * @param year
 * @param month
 * @return monthly totals for store sales for all members
 */
    public double storeTotalsByYearAndMonth(int year, int month) {
        Double storeTotals = memberPurchasesRepository.storeTotalsByYearAndMonth(year, month);
        if (storeTotals == null) {
            return 0;
        } else {
            return (double) storeTotals;
        }
    }

    /**
     *
     * @param year
     * @param month
     * @return monthly totals for all vets service sales for all members
     */
    public double vetAmountByYearAndMonth(int year, int month) {
        Double vetTotals = memberPurchasesRepository.vetAmountByYearAndMonth(year, month);
        if (vetTotals == null) {
            return 0;
        } else {
            return (double) vetTotals;
        }
    }

    /**
     * fetches the member margins
     *
     * @param year
     * @param month
     * @param id unique member identifier
     * @return memberRecord object
     */
    public Map<?,?> memberMargins(int year, int month, long id) {
        Map <String,Double> memberRecord = new HashMap<>();
        Double deductions = deductionsService.sumDeductionsByYearAndMonth(year, month);
        double milkAmount = milkService.sumByIdAndMonth(id, month, year);
        double storePurchases = memberStoreTotalsByYearAndMonth(year, month, id);
        double vetPurchases = memberVetTotalsByYearAndMonth(year, month, id);
        memberRecord.put("deductions",deductions);
        memberRecord.put("milkAmount", milkAmount*30.0);
        memberRecord.put("storePurchases",storePurchases);
        memberRecord.put("vetPurchases",vetPurchases);
        return memberRecord;
    }

    /**
     * deletes a memberPurchase
     *
     * @param salesId unique sales identifier
     */
    public void delete(String salesId) {
        MemberPurchases purchase = findById(salesId);
        memberPurchasesRepository.delete(purchase);
    }
/**
 * finds a purchase
 * @param salesId unique sales identifier
 * @return  member purchase
 */
    public MemberPurchases findById(String salesId) {
        Optional<MemberPurchases> purchases = memberPurchasesRepository.findBySalesId(salesId);
       return purchases.orElseThrow(()->{
            return new ResourceNotFoundException("");
        });
    }

    /**
     * updates a member purchase
     *
     * @param memberPurchase object
     * @param salesId unique sales identifier
     * @param authentication Authentication object
     * @return update member purchase object
     */
    public MemberPurchases update(MemberPurchases memberPurchase, String salesId, Authentication authentication) {
        MemberPurchases purchase_ = findById(salesId);
        purchase_.setAmount(memberPurchase.getAmount());
        purchase_.setUser(userService.findUserById(authentication.getName()));
        purchase_.setType(memberPurchase.getType());
        return memberPurchasesRepository.save(purchase_);
    }
}
