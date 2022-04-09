package com.repository;

import com.model.UserRole;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * SpringJPA repository interface for the UserRoles class
 *
 * UserRolesRepository.java
 *
 * @author Samuel Maina
 *
 * 10-20-2022
 *
 * @version 1.0
 */
public interface UserRolesRepository extends CrudRepository<UserRole, Long> {

    /**
     * selects all roles assigned to a given user.
     *
     * @param UserId unique user identifier
     * @return UserRole
     */
    @Query("from UserRole m where m.user.id=?1")
    public List<UserRole> findUserRolesByUserId(String UserId);

    /**
     * Deletes a UserRole
     *
     * @param userRoleId
     */
    @Modifying
    @Transactional
    @Query("delete from UserRole m where m.id=?1")
    public void deleteUserRole(long userRoleId);

    /**
     * deletes all userRoles assigned to a given user
     *
     * @param userId
     */
    @Modifying
    @Transactional
    @Query("delete from UserRole m where m.user.id=?1")
    public void deleteRolesByUserId(String userId);

}
