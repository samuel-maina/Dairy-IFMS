package com.controller;

import com.service.SalesService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for path purchases
 *
 * StoreController.java
 *
 * @author Samuel Maina
 *
 * 03/10/2022
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/purchases")
public class StoreController {

    @Autowired
    private SalesService memberPurchasesService;

    /**
     * retrieve member total purchases by month
     *
     * @param year
     * @param month
     * @param id unique user identifier
     * @param principal spring security credential holder
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> getMemberTotalPurchaseByMonth(@RequestParam int year, @RequestParam int month, @RequestParam Long id, Principal principal) {
        return new ResponseEntity<>(memberPurchasesService.storeTotalsByYearAndMonth(year, month), HttpStatus.OK);
    }

}
