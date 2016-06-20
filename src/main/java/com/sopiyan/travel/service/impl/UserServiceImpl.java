/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sopiyan.travel.service.impl;

import com.sopiyan.travel.model.entity.User;
import com.sopiyan.travel.service.repository.LoginRepository;
import com.sopiyan.travel.model.dto.UserDto;
import com.sopiyan.travel.service.services.UserService;

import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


/**
 * @author Null Pointer
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public Optional<User> getLoginById(String id) {
        return Optional.ofNullable(loginRepository.findOne(id));
    }

    @Override
    public Optional<User> getLoginByEmail(String email) {
        return loginRepository.findByEmail(email);
    }

    @Override
    public Page<User> getAllLogin(Pageable page) {
        return loginRepository.findAll(page);
    }

    @Override
    public User create(UserDto userDto) {
        User user = userDto.convertToUser();
        return loginRepository.save(user);
    }

    @Override
    public User cariUSer(String id) {
        return loginRepository.findOne(id);
    }

    @Override
    public void update(User user) {
        loginRepository.save(user);
    }
}
