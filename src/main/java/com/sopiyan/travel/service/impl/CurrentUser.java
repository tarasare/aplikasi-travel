/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sopiyan.travel.service.impl;

import com.sopiyan.travel.model.entity.User;
import com.sopiyan.travel.util.enumuration.Roles;
import org.springframework.security.core.authority.AuthorityUtils;

/**
 *
 * @author Sopiyan
 */
public class CurrentUser extends org.springframework.security.core.userdetails.User {
    private User user;
    public CurrentUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().toString()));
        this.user = user;
    }


    public User getUser() {
        return user;
    }

    public String getId() {
        return user.getIdUser();
    }

    public Roles getRole() {
        return user.getRole();
    }

}
