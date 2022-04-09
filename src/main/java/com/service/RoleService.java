/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.service;

import com.exceptions.ResourceNotFoundException;
import com.model.Role;
import com.repository.RoleRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author samuel
 */
@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    
    public Role findById(String Id){
        Role role= null;
        Optional<Role> roleOp = roleRepository.findById(Id);
        if(roleOp.isPresent()){
          role= roleOp.get();
        }else{
            throw new ResourceNotFoundException("");
        }
        return role;
    }
    
}
