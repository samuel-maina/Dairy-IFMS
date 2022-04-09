package com.repository;

import com.model.Deductions;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Class contains database custom implementations for the deductions data layer
 *
 * @author Samuel Maina
 * 
 * 02-20-2022 
 * 
 * DeductionsRepository.java
 * 
 * @version 1.0
 */
@Repository
public interface DeductionsRepository extends JpaRepository<Deductions, Long> {

    /**
     * fetches sum from the database based on @param year and @param month
     *
     * @param year
     * @param month
     * @return Double sum
     */
    @Query("select sum(m.amount) from Deductions m where  YEAR(m.date)=?1 and MONTH(m.date) = ?2")
    public Double sumDeductionsByYearMonth(int year, int month);

    /**
     * fetches all deductions based on year and month
     *
     * @param year
     * @param month
     * @return deductions list.
     */
    @Query("from Deductions m where YEAR(m.date)=?1 and MONTH(m.date)=?2")
    public List<Deductions> getByYearAndMonth(int year, int month);

}
