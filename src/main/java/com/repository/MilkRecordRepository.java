package com.repository;

import org.springframework.data.repository.CrudRepository;
import com.model.MilkRecord;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;

/**
 * SpringJPA Repository interface for the MilkRecords class
 *
 * @author Samuel Maina
 *
 * MilkRecordPository.java
 *
 * 01/13/2022
 *
 * @version 1.0
 */
public interface MilkRecordRepository extends CrudRepository<MilkRecord, Long> {

    /**
     * Checks if a record exists based on date and member ID
     *
     * @param date
     * @param MemberId
     * @return MilkRecord
     */
    @Query("select s from MilkRecord s where s.date=?1 and s.member.id=?2")
    public MilkRecord checkExists(LocalDate date, long MemberId);

    /**
     * selects all milk records entered in a given month
     *
     * @param year
     * @param month
     * @return MilkRecords
     */
    @Query("from MilkRecord m where YEAR(m.date)=?1 and MONTH(m.date)=?2")
    public List<MilkRecord> getMilkRecordsByYearMonth(int year, int month);

    /**
     * Select a member record entered on a given date
     *
     * @param date
     * @param id
     * @return MilkRecord
     */
    @Query("select m from MilkRecord m where m.date= ?1 and m.member.id=?2")
    public MilkRecord selectByDateAndID(LocalDate date, long id);

    /**
     * finds a member's total sum for a given month
     *
     * @param id
     * @param year
     * @param month
     * @return sums
     */
    @Query("select sum(m.amount) from MilkRecord m where m.member.id = ?1 and YEAR(m.date)=?2 and MONTH(m.date) = ?3")
    public Double sumMilkByMonthYearId(long id, int year, int month);

    /**
     * selects all member records for a given month
     *
     * @param year
     * @param month
     * @param id
     * @return MilkRecords
     */
    @Query("select M from MilkRecord M where YEAR(M.date)=?1 and MONTH(M.date)=?2 and M.member.id=?3 ORDER BY M.date")
    public List<MilkRecord> getRecordsByMemberAndMonth(int year, int month, long id);

    /**
     * finds the number of days a member appears on the milk records register
     * for a given month
     *
     * @param year
     * @param month
     * @param memberID
     * @return count
     */
    @Query("select COUNT(m) from MilkRecord m where YEAR(m.date)=?1 and MONTH(m.date)=?2 and m.member.id=?3")
    public int countPerMonth(int year, int month, long memberID);

    /**
     * selects milkRecord by Id
     *
     * @param id
     * @return milkRecord
     */
    @Query("Select m from MilkRecord m where m.id=?1")
    public Optional<MilkRecord> findRecordById(String id);

    /**
     * finds the total amount of milk collected in a given month
     *
     * @param year
     * @param month
     * @return sum
     */
    @Query("select SUM(m.amount) from MilkRecord m where YEAR(m.date)=?1 and MONTH(m.date)=?2")
    public Double monthlySum(int year, int month);
}
