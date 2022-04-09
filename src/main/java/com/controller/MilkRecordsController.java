package com.controller;

import com.model.MilkRecord;
import com.service.MilkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Handler for milk records
 *
 * @author Samuel Maina
 *
 * 2022-01-09
 *
 * @version 1.0
 *
 * MilkRecordsController.java
 */
@RestController
@RequestMapping("/api/v1/records")
public class MilkRecordsController {

    @Autowired
    private MilkService milkService;

    /**
     * finds member record by date
     *
     * @param date
     * @param id unique member identifier
     * @return milk record
     */
    @GetMapping("/memberrecordbydate")
    public ResponseEntity<?> retriveByDateandMemberid(@RequestParam String date, @RequestParam String id) {

        return new ResponseEntity<>(milkService.getByDateandId(date, Long.parseLong(id)), HttpStatus.OK);
    }

    /**
     * Retrieves member monthly records
     *
     * @param id unique member identifier
     * @param month
     * @param year
     * @return list of member milk records
     */
    @GetMapping("/membermonthlyrecords")
    public ResponseEntity<?> retrieveMemberMonthlyRecords(@RequestParam long id, @RequestParam int month, @RequestParam int year) {
        return new ResponseEntity<>(milkService.retriveMemberRecords(year, month, id), HttpStatus.OK);
    }

    /**
     * Finds the total amount of milk sold by a given person in a given month
     *
     * @param id unique member identifier
     * @param month
     * @param year
     * @return sum
     */
    @GetMapping("/sum")
    public ResponseEntity<?> sum(@RequestParam long id, @RequestParam int month, @RequestParam int year) {
        return new ResponseEntity<>(milkService.sumByIdAndMonth(id, month, year), HttpStatus.OK);
    }

    /**
     * Finds total amount collected in a month
     *
     * @param month
     * @param year
     * @return sum
     */
    @GetMapping("/monthlysum")
    public ResponseEntity<?> monthlySum(@RequestParam int month, @RequestParam int year) {
        return new ResponseEntity<>(milkService.monthlySum(year, month), HttpStatus.OK);
    }

    /**
     * Counts the number of milk records that belong to a given person in a
     * given month
     *
     * @param id unique member identifier
     * @param month
     * @param year
     * @return count
     */
    @GetMapping("/count")
    public ResponseEntity<?> count(@RequestParam long id, @RequestParam int month, @RequestParam int year) {
        return new ResponseEntity<>(milkService.countPerMonth(year, month, id), HttpStatus.OK);
    }

    /**
     * Saves a new record
     *
     * @param record
     * @param id unique member identifier
     * @param authentication
     * @return MilkRecord
     */
    @PostMapping("/{id}")
    public ResponseEntity<?> save(@RequestBody MilkRecord record, @PathVariable Long id, Authentication authentication) {
        return new ResponseEntity<>(milkService.save(record, id, authentication), HttpStatus.OK);
    }

    /**
     * updates a milk record
     *
     * @param record
     * @param id unique member identifier
     * @param authentication
     * @return milkRecord
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody MilkRecord record, @PathVariable String id, Authentication authentication) {
        return new ResponseEntity<>(milkService.update(record, id, authentication), HttpStatus.OK);
    }

    /**
     * deletes a milk record
     *
     * @param id unique member identifier
     * @return HttpStatus object
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        milkService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
