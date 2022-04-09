package com.controller;

import com.model.MilkRecord;
import com.service.MilkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

/**
 * Handler for Http routing for the path milk_record
 * @author Samuel Maina
 * MemberMilkController.java
 * 03/10/2022
 */
@RestController
@RequestMapping("/api/v1/mobile/milk_record")
public class MobileMilkRecordController {

    @Autowired
    private MilkService milkService;

    /**
     * Saves a milk record
     *
     * @param milkRecord MilkRecord instance
     * @param memberId unique member identifier
     * @param authentication Spring security authentication instance
     * @return MilkRecord
     */
    @PostMapping("/{memberId}")
    public ResponseEntity<?> saveRecord(@RequestBody MilkRecord milkRecord, @PathVariable long memberId, Authentication authentication) {
        return new ResponseEntity<>(milkService.mobileSave(milkRecord, memberId, authentication), HttpStatus.OK);
    }
        /**
     * deletes a milk record
     *
     * @param id unique milkRecord identifier
     * @return HttpStatus.OK
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        milkService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    

}
