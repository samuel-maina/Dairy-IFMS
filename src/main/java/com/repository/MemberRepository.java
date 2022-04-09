
package com.repository;

import com.model.Member;
import org.springframework.data.repository.CrudRepository;

/**
 * SpringJPA repository interface for the Member class
 * 
 * @author Samuel Maina
 * 
 * MemberRepository.java
 * 
 * 08-12-2021
 * 
 * @version 1.0
 */
public interface MemberRepository extends CrudRepository<Member, Long> {


}
