/*
 * This file is written by and is property of Samuel Maina Mwangi.
 */
package com.repository;

import com.model.MemberPurchases;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * SpringJPA Repository interface for the MemberPurchases class
 *
 * MemberPurchasesRepository.java
 *
 * @author Samuel Maina
 *
 * 02-20-2022
 *
 * @version 1.0
 */
public interface MemberPurchasesRepository extends CrudRepository<MemberPurchases, Long> {

    /**
     * gets an unconfirmed member purchase
     *
     * @param code purchases unique identifier
     * @param id member unique identifier
     * @return MemberPurchases
     */
    @Query("from MemberPurchases m where m.confirmationCode=?1 and m.member.id=?2 and m.confirmed=false")
    public MemberPurchases unconfirmedPurchase(String code, long id);

    /**
     * finds the sum of store purchases made by a member in a given month
     *
     * @param year
     * @param month
     * @param id member unique identifier
     * @return sum
     */
    @Query("select sum(m.amount) from MemberPurchases m where  YEAR(m.date)=?1 and MONTH(m.date) = ?2 and m.member.id = ?3 and m.confirmed = true and m.type=0")
    public Integer memberStorePurchaseTotalsByYearAndMonth(int year, int month, long id);

    /**
     * finds the sum of vet purchases made by a member in a given month
     *
     * @param year
     * @param month
     * @param id unique member identifier
     * @return sum
     */
    @Query("select sum(m.amount) from MemberPurchases m where  YEAR(m.date)=?1 and  MONTH(m.date) = ?2 and m.member.id = ?3 and m.confirmed = true and m.type=1")
    public Integer memberVetPurchaseTotalsByYearAndMonth(int year, int month, long id);

    /**
     * Finds the total sum of store sales in a given month
     *
     * @param year
     * @param month
     * @return sum
     */
    @Query("select sum(m.amount) from MemberPurchases m where  YEAR(m.date)=?1 and MONTH(m.date) = ?2 and m.confirmed = true and m.type=0")
    public Double storeTotalsByYearAndMonth(int year, int month);

    /**
     * Finds the total sum of vet sales in a given month
     *
     * @param year
     * @param month
     * @return sum
     */
    @Query("select sum(m.amount) from MemberPurchases m where YEAR(m.date)=?1 and MONTH(m.date) = ?2 and m.confirmed = true and m.type=1")
    public Double vetAmountByYearAndMonth(int year, int month);

    /**
     * selects a memberPuchase by its unique identifier
     *
     * @param salesId unique sales/purchase identifier
     * @return MemberPurchase
     */
    @Query("from MemberPurchases M where M.confirmationCode=?1")
    public Optional<MemberPurchases> findBySalesId(String salesId);

}
