/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sopiyan.travel.service.repository;

import com.sopiyan.travel.model.entity.User;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Null Pointer
 */
@Repository
public interface LoginRepository extends JpaRepository<User, String>{
    Optional<User> findByEmail(String email);
}
