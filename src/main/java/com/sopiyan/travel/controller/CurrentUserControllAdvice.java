package com.sopiyan.travel.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by Sopiyan on 26/05/2016.
 */
@ControllerAdvice
public class CurrentUserControllAdvice {
    @ModelAttribute("currentUser")
    public UserDetails getCurrentUser(Authentication authentication){
        return (authentication != null) ?(UserDetails) authentication.getPrincipal():null;
    }
}
