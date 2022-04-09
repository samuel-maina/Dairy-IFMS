package com.controller;

import com.model.MemberPurchases;
import com.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
 * Controller for all sales operations 
 * 
 * SalesController.java
 *
 * @author Samuel Maina
 * 
 * 2022-01-13
 * 
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/mobile/sales")
public class SalesController {

    @Autowired
    private SalesService salesService;

    /**
     * Saves a new purchase
     *
     * @param purchases member purchase object
     * @param id
     * @param authentication object
     * @return MemberPurchases object
     */
    @PostMapping("/{id}")
    public ResponseEntity<?> save(@RequestBody MemberPurchases purchases, @PathVariable long id, Authentication authentication) {
        return new ResponseEntity<>(salesService.save(purchases, id, authentication), HttpStatus.OK);
    }

    /**
     * confirms a sale The unique code of each sales is used to confirm each
     * sale.
     *
     * @param id
     * @param confirmationCode
     * @return confirmed sale
     */
    @PutMapping
    public ResponseEntity<?> confirmStoreSales(@RequestParam long id, @RequestParam String confirmationCode) {
        return new ResponseEntity<>(salesService.confirmSale(id, confirmationCode), HttpStatus.OK);
    }

    /**
     * finds all the purchases vs milk amount a member has
     *
     * @param year
     * @param month
     * @param id
     * @return member margins
     */
    @GetMapping("/margins")
    public ResponseEntity<?> memberMargins(@RequestParam int year, @RequestParam int month, @RequestParam long id) {
        return new ResponseEntity<>(salesService.memberMargins(year, month, id), HttpStatus.OK);

    }

    /**
     * deletes a sale by sales Id
     *
     * @param salesId
     * @return HttpStatus code
     */
    @DeleteMapping("/{salesId}")
    public ResponseEntity<?> delete(@PathVariable String salesId) {
        salesService.delete(salesId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * find member purchase by id
     *
     * @param salesId
     * @return member purchase
     */
    @GetMapping("/{salesId}")
    public ResponseEntity<?> get(@PathVariable String salesId) {
        return new ResponseEntity<>(salesService.findById(salesId), HttpStatus.OK);
    }

    /**
     * update a member purchase
     *
     * @param purchases
     * @param salesId
     * @param authentication object
     * @return updated member object
     */
    @PutMapping("/{salesId}")
    public ResponseEntity<?> update(@RequestBody MemberPurchases purchases, @PathVariable String salesId, Authentication authentication) {
        return new ResponseEntity<>(salesService.update(purchases, salesId, authentication), HttpStatus.OK);
    }

}
