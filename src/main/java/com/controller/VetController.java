package com.controller;

import com.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for path /vet
 *
 * VetController.java
 *
 * @author Samuel Maina
 *
 * @version 1.0
 *
 * 03/10/2022
 */
@RestController
@RequestMapping("/api/v1/vet")
public class VetController {

    @Autowired
    private SalesService memberPurchasesService;

    /**
     * finds the total amount of vet purchases
     *
     * @param year
     * @param month
     * @param id unique user identifier
     * @return ResponseEntity
     */
    @GetMapping
    public ResponseEntity<?> vetAmount(@RequestParam int year, @RequestParam int month, @RequestParam Long id) {
        return new ResponseEntity<>(memberPurchasesService.vetAmountByYearAndMonth(year, month), HttpStatus.OK);
    }

}
