package com.service;

import com.Application;
import com.exceptions.ResourceNotFoundException;
import com.model.Deductions;
import com.repository.DeductionsRepository;
import java.security.Principal;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for the Deductions Object Handles all the work between the
 * DeductionsController and the database implementations
 *
 * @author samuel 1/20/2022 DeductionsService.java
 * @version 1.0
 */
@Service
public class DeductionsService {

    @Autowired
    private DeductionsRepository deductionsRepository;

    

    /**
     * fetches the sum of all deduction by year and month
     *
     * @param year
     * @param month
     * @return double sum or 0.0
     */
    public Double sumDeductionsByYearAndMonth(int year, int month) {
        Double sum = deductionsRepository.sumDeductionsByYearMonth(year, month);
        if (sum == null) {

            return 0.0;
        }
        return sum;
    }

    /**
     * Fetches all deductions based on year and month
     *
     * @param year
     * @param month
     * @return List
     */
    public List<Deductions> getByYearAndMonth(int year, int month) {
        return deductionsRepository.getByYearAndMonth(year, month);
    }

    /**
     * Deletes a deductions object based on unique ID
     *
     * @param id
     * @param principal
     */
    public void delete(long id, Principal principal) {
        Optional<Deductions> deduction = deductionsRepository.findById(id);
        if (deduction.isPresent()) {
            deductionsRepository.delete(deduction.get());
        } else {
            Application.logger.error("Delete deduction fail id = " + id + " Agent = " + principal.getName());
            throw new ResourceNotFoundException("");
        }
    }

    /**
     * update and deductions object
     *
     * @param deduction
     * @param id
     * @param principal
     */
    public void update(Deductions deduction, long id, Principal principal) {
        Optional<Deductions> deduction_ = deductionsRepository.findById(id);
        if (deduction_.isPresent()) {
            Deductions temp = deduction_.get();
            temp.setAmount(deduction.getAmount());
            temp.setDate(deduction.getDate());
            temp.setDescription(deduction.getDescription());
            deductionsRepository.save(temp);
        } else {
            Application.logger.error("update deduction failed = " + id + " Agent = " + "Dks samiel");
            throw new ResourceNotFoundException("");
        }
    }

    /**
     * creates a deductions object
     *
     * @param deduction
     * @return
     */
    public Deductions save(Deductions deduction) {
        return deductionsRepository.save(deduction);
    }

}
