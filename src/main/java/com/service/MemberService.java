package com.service;
import com.exceptions.ResourceAlreadyExistException;
import com.exceptions.ResourceNotFoundException;
import com.model.Member;
import com.repository.MemberRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author samuel
 */
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    /**
     * Saves a new member
     *
     * @param member
     * @return saved member object
     */
    public Member saveMember(Member member) {
        Optional<Member> member_ = memberRepository.findById(member.getId());
        if (member_.isPresent()) {
            throw new ResourceAlreadyExistException("");
        }
        return memberRepository.save(member);
    }

    /**
     * finds a member object
     *
     * @param memberNumber
     * @return member object
     */
    public Member findById(long memberNumber) {
        Optional<Member> member = memberRepository.findById(memberNumber);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new ResourceNotFoundException("");
        }
    }

    /**
     * deletes a member object
     *
     * @param memberId
     */
    public void delete(long memberId) {
        Member member = findById(memberId);
        memberRepository.delete(member);
    }

    /**
     * updates a member object
     *
     * @param memberId
     * @param member
     * @return updated member object
     */
    public Member update(long memberId, Member member) {
        Member member_ = findById(memberId);
        member_.setName(member.getName());
        member_.setAltPhoneNumber(member.getAltPhoneNumber());
        member_.setNationalId(member.getNationalId());
        member_.setPhoneNumber(member.getPhoneNumber());
        return memberRepository.save(member_);
    }

}
