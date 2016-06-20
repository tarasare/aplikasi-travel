/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sopiyan.travel.service.services;

        import com.sopiyan.travel.model.entity.User;
        import com.sopiyan.travel.model.dto.UserDto;
        import org.springframework.data.domain.Page;
        import org.springframework.data.domain.Pageable;
        import java.util.Optional;

/**
 *
 * @author Null Pointer
 */
public interface UserService {
    Optional<User> getLoginById(String id);
    Optional<User> getLoginByEmail(String email);
    Page<User> getAllLogin(Pageable page);
    User create(UserDto userDto);
    void update(User user);
    User cariUSer(String id);

}
