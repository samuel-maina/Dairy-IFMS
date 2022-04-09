package com.repository;

import com.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

/**
 * SpringJPA repository interface for the User class
 *
 * UserRepository.java
 *
 * @author Samuel Maina
 *
 * 20-01-2022
 *
 * @version 1.0
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    /**
     * finds a user by Id
     *
     * @param id unique user identifier
     * @return User
     */
    public Optional<User> findById(int id);

    /**
     * finds if a user exists
     *
     * @param id unique user identifier
     * @return boolean
     */
    public boolean existsById(int id);

    /**
     * finds user by username
     *
     * @param userId unique user identifier
     * @return User
     * @throws UsernameNotFoundException
     */
    public Optional<User> findById(String userId) throws UsernameNotFoundException;

    /**
     * finds all user accounts that are deactivated at a given time
     *
     * @param time
     * @return Users
     */
    @Query("Select M from User M where M.scheduledAccountActivator.deactivationTime =?1 ")
    public List<User> findByScheduledTime(String time);

}
