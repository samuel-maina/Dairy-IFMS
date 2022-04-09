package com.service;

import com.Application;
import com.exceptions.ResourceAlreadyExistException;
import com.exceptions.ResourceNotFoundException;
import com.model.Member;
import com.model.MilkRecord;
import com.model.StringGen;
import com.model.User;
import com.repository.MilkRecordRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
 * Service class for the MilkRecords
 *
 * @author Samuel Maina MilkService.java 2022-01-13
 * @version 1.0
 */
@Service
@Transactional
public class MilkService {

    @Autowired
    private MilkRecordRepository milkRecordRepository;

    @Autowired
    private MemberService memberService;

    @Autowired
    private UserService userService;

    /**
     * saves milk records from mobile clients
     *
     * @param milkRecord MilkRecord object
     * @param memberId uniqe member identifier
     * @param authentication Spring Authentication object
     * @return milk record object
     */
    @Transactional
    public MilkRecord mobileSave(MilkRecord milkRecord,long memberId, Authentication authentication) {
        Future<MilkRecord> temp = null;
        LocalDate date = LocalDate.now();
        if (exists(memberId, date)) {
            throw new ResourceAlreadyExistException("The entry exists");
        } else {
            
            milkRecord.setTransactionId(StringGen.createString());
            milkRecord.setDate(date);
            Member member_ = memberService.findById(memberId);
            User user = userService.findUserById(authentication.getName());
            milkRecord.setUser(user);
            milkRecord.setMember(member_);
            ExecutorService pool = Executors.newFixedThreadPool(1);
            temp = pool.submit(new Callable<MilkRecord>() {

                @Override
                public MilkRecord call() throws Exception {
                    MilkRecord responseMilkRecord = milkRecordRepository.save(milkRecord);
                    Application.logger.info(responseMilkRecord);
                    return responseMilkRecord;
                }
            });
        }
        MilkRecord temp_ = null;
        try {
            temp_ = temp.get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(MilkService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return temp_;

    }

    /**
     * checks if a records exists among the records
     *
     * @param memberNumber
     * @param date
     * @return boolean
     */
    private boolean exists(long id, LocalDate date) {
        return milkRecordRepository.checkExists(date, id) != null;

    }

    /**
     * finds all records that fall within given year and date
     *
     * @param year
     * @param month
     * @return list of milk records
     */
    public List<MilkRecord> getAllByYearMonth(int year, int month) {
        return milkRecordRepository.getMilkRecordsByYearMonth(year, month);
    }

    /**
     * finds a members records for a given date.only for a single member.
     *
     * @param date
     * @param id
     * @return milk record
     */
    public MilkRecord getByDateandId(String date, long id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
        LocalDate searchDate = LocalDate.parse(date, formatter);
        MilkRecord record = milkRecordRepository.selectByDateAndID(searchDate, id);
        if (record == null) {
            throw new ResourceNotFoundException("");
        }
        return record;

    }

    /**
     * finds the total amount of milk sold by a member on a given day
     *
     * @param id
     * @param month
     * @param year
     * @return sum
     */
    public Double sumByIdAndMonth(long id, int month, int year) {
        Double p = milkRecordRepository.sumMilkByMonthYearId(id, year, month);
        if (p == null) {
            return 0.0;
        }
        return (double) p;
    }

    /**
     * finds all monthly records for a given member
     *
     * @param year
     * @param month
     * @param id
     * @return list of milk records
     */
    public List<MilkRecord> retriveMemberRecords(int year, int month, long id) {
        return milkRecordRepository.getRecordsByMemberAndMonth(year, month, id);
    }

    /**
     * finds the number of days in a month a member has sold his milk
     *
     * @param year
     * @param month
     * @param memberID
     * @return count
     */
    public int countPerMonth(int year, int month, long memberID) {
        return milkRecordRepository.countPerMonth(year, month, memberID);
    }

    /**
     * deletes a record from the milk records
     *
     * @param id unique resource identifier
     */
    public void delete(String id) {
        MilkRecord record = findById(id);
        milkRecordRepository.delete(record);
    }

    /**
     * finds a record
     *
     * @param id unique resource identifier
     * @return milk record object
     */
    private MilkRecord findById(String id) {
        Optional<MilkRecord> record = milkRecordRepository.findRecordById(id);
        return record.orElseThrow(()-> new ResourceNotFoundException(""));  
    }

    /**
     * update a milk record
     *
     * @param record
     * @param id
     * @param authentication
     * @return milk record
     */
    public MilkRecord update(MilkRecord record, String id, Authentication authentication) {
        MilkRecord record_ = findById(id);
        record_.setAmount(record.getAmount());
        record_.setEntryGroup(record.getEntryGroup());
        record_.setUser(userService.findUserById(authentication.getName()));
        return milkRecordRepository.save(record_);
    }

    /**
     * Saves a milk record from a browser terminal
     *
     * @param record
     * @param id
     * @param authentication
     * @return milk record
     */
    public MilkRecord save(MilkRecord record, long id, Authentication authentication) {
        if (exists(id, record.getDate())) {
            throw new ResourceAlreadyExistException("");
        }
        User user = userService.findUserById(authentication.getName());
        Member member = memberService.findById(id);
        record.setMember(member);
        record.setUser(user);
        record.setTransactionId(StringGen.createString());
        return milkRecordRepository.save(record);
    }

    /**
     * finds total amount of milk collected in a month
     *
     * @param year
     * @param month
     * @return sum
     */
    public Double monthlySum(int year, int month) {
        Double sum = milkRecordRepository.monthlySum(year, month);
        if (sum == null) {
            return 0.0;
        }
        return milkRecordRepository.monthlySum(year, month);
    }
}
