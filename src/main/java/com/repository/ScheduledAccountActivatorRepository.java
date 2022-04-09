package com.repository;

import com.model.ScheduledAccountActivator;
import org.springframework.data.repository.CrudRepository;

/**
 * SpringJPA repository interface for the ScheduledAccountActivator class
 *
 * ScheduledAccountActivatorRepository.java
 *
 * @author samuel Maina
 *
 * 12-23-2021
 *
 * @version 1.0
 */
public interface ScheduledAccountActivatorRepository extends CrudRepository<ScheduledAccountActivator, Long> {
    
}
