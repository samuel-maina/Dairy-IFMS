package com.controller;

import com.model.Deductions;
import com.service.DeductionsService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Member deduction handler
 *
 * @author Samuel Maina
 *
 * 1/20/2022
 *
 * DeductionsController.java
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/deductions")
public class DeductionsController {

    @Autowired
    private DeductionsService deductionsService;

    /**
     * Fetches a list of deductions based by year and month.
     *
     * @param year
     * @param month
     * @return list
     */
    @GetMapping
    public ResponseEntity<?> deductions(@RequestParam int year, @RequestParam int month) {
        return new ResponseEntity<>(deductionsService.getByYearAndMonth(year, month), HttpStatus.OK);
    }

    /**
     * Fetches sum of all deductions in a given month
     *
     * @param year
     * @param month
     * @return sum
     */
    @GetMapping("/sum")
    public ResponseEntity<?> sum(@RequestParam int year, @RequestParam int month) {
        return new ResponseEntity<>(deductionsService.sumDeductionsByYearAndMonth(year, month), HttpStatus.OK);
    }

    /**
     * Deletes a deductions object
     *
     * @param id
     * @param principal
     * @return HttpStatus fail or success
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Principal principal) {
        deductionsService.delete(id, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Updates a deductions object
     *
     * @param deduction
     * @param id
     * @param principal
     * @return HttpStatus fail or success
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Deductions deduction, @PathVariable Long id, Principal principal) {
        deductionsService.update(deduction, id, principal);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * creates a new deductions object
     *
     * @param deduction
     * @return deductions object and HttpStatus 200
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Deductions deduction) {
        return new ResponseEntity<>(deductionsService.save(deduction), HttpStatus.OK);
    }

}
