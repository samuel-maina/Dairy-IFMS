package com.controller;

import com.model.Member;
import com.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Handles all Http routing for member path requests
 *
 * MemberController.java
 *
 * @author Samuel Maina
 *
 * 03-10-2022
 *
 * @version 1.0
 */
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {

    @Autowired
    MemberService memberService;

    /**
     * Saves a new member
     *
     * @param member Member object/instance
     * @return ResponseEntity
     */
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Member member) {
        return new ResponseEntity<>(memberService.saveMember(member), HttpStatus.OK);
    }

    /**
     * Fetches a member object by id
     *
     * @param memberId unique member identifier
     * @return ResponseEntity
     */
    @GetMapping("/{memberId}")
    public ResponseEntity<?> find(@PathVariable long memberId) {
        return new ResponseEntity<>(memberService.findById(memberId), HttpStatus.OK);
    }

    /**
     * deletes member object
     *
     * @param memberId unique member identifier
     * @return ResponseEntity
     */
    @DeleteMapping("/{memberId}")
    public ResponseEntity<?> delete(@PathVariable long memberId) {
        memberService.delete(memberId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * updates member object
     *
     * @param memberId unique member identifier
     * @param member Member object/instance
     * @return updated member object
     */
    @PutMapping("/{memberId}")
    public ResponseEntity<?> update(@PathVariable long memberId, @RequestBody Member member) {
        return new ResponseEntity<>(memberService.update(memberId, member), HttpStatus.OK);
    }

}
