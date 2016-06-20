/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sopiyan.travel.util.validator;


import com.sopiyan.travel.model.dto.UserDto;
import com.sopiyan.travel.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Null Pointer
 */
@Component
public class UserFormValidator implements Validator {

    @Autowired
    private UserService userService;
    @Override
    public boolean supports(Class<?> clazz) {
    return clazz.equals(UserDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
    UserDto createLogin = (UserDto) target;
        validatePasswords(errors, createLogin);
        validateEmails(errors, createLogin);
    }
    private void validatePasswords(Errors errors, UserDto createLogin){
        if(!createLogin.getPassword().equals(createLogin.getPasswordRepeat())){
            errors.reject("password.no_match","Password do not match");
        }
    }
    private void validateEmails(Errors errors, UserDto createLogin){
        if(userService.getLoginByEmail(createLogin.getEmail()).isPresent()){
            errors.reject("email.exists", "User with this email already exists");
        }
    }
}
