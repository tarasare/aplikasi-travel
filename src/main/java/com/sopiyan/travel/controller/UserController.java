/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sopiyan.travel.controller;

import java.util.List;
import java.util.Optional;

import com.sopiyan.travel.model.dto.*;
import com.sopiyan.travel.model.entity.Kota;
import com.sopiyan.travel.model.entity.Rute;
import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.model.entity.User;
import com.sopiyan.travel.service.services.KotaService;
import com.sopiyan.travel.service.services.RuteService;
import com.sopiyan.travel.service.services.TerminalService;
import com.sopiyan.travel.service.services.UserService;
import com.sopiyan.travel.util.enumuration.Roles;
import com.sopiyan.travel.util.paging.PageItem;
import com.sopiyan.travel.util.paging.PageWrapper;
import com.sopiyan.travel.util.validator.UserFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 *
 * @author Sopiyan
 */
@Controller
public class UserController {
    private UserService userService;
    private UserFormValidator userFormValidator;
    private KotaService kotaService;
    private TerminalService terminalService;
    private RuteService ruteService;
    @Autowired
    public UserController(UserService userService, UserFormValidator userFormValidator, KotaService kotaService,TerminalService terminalService,RuteService ruteService){
        this.userFormValidator = userFormValidator;
        this.userService=userService;
        this.kotaService=kotaService;
        this.terminalService=terminalService;
        this.ruteService = ruteService;
    }
    @InitBinder("userDto")
    public void initBinder(WebDataBinder binder){
        binder.addValidators(userFormValidator);
    }
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postRegisterUser(@Valid @ModelAttribute("userDto") UserDto userDto){
        userService.create(userDto);
        return "redirect:login";
    }
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getPageRegisterUser(Model m, @ModelAttribute("currentUser")UserDetails userDetails){
        User user =(userDetails != null ?userService.getLoginByEmail(userDetails.getUsername()).get(): null );
        if(user == null){
            m.addAttribute("userDto",new UserDto());
            return "register";
        }else{
            return "redirect:main";
        }

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getPageLogin(@RequestParam Optional<String> error, Model m, @ModelAttribute("currentUser")UserDetails userDetails){
        if(userDetails == null){
            boolean e = error.isPresent();
            m.addAttribute("e",e);
            return "login";
        }else{
            return "redirect:main";
        }

    }
    @RequestMapping(value = "/main", method = RequestMethod.GET)
    public String getProfile(@ModelAttribute("currentUser")UserDetails userDetails,Model m){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){

            if(user.getRole() == Roles.ADMIN){
                return "redirect:dashboard/main";
            }else{
                m.addAttribute("user", user);
                return "main";
            }
        }else{
            return "login";
        }
    }
    @RequestMapping(value = "/dashboard/main", method = RequestMethod.GET)
    public void getAdminIndex(Model m, @ModelAttribute("currentUser")UserDetails userDetails, Pageable pageable){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            m.addAttribute("user", user);
        }
        Page<Terminal> jumlahTerminal = terminalService.getAllTerminal(pageable);
        Page<Rute> jumlahRute = ruteService.getAllRute(pageable);
        Page<Kota> jumlahKota = kotaService.getAllKota(pageable);
        Page<User> jumlahUser = userService.getAllLogin(pageable);

        m.addAttribute("jumlahTerminal", jumlahTerminal.getTotalElements());
        m.addAttribute("jumlahRute", jumlahRute.getTotalElements());
        m.addAttribute("jumlahKota", jumlahKota.getTotalElements());
        m.addAttribute("jumlahUser", jumlahUser.getTotalElements());


    }
    @RequestMapping(value = "/profile", method = RequestMethod.PUT)
    public String updateProfile(User user){
        User u = userService.getLoginById(user.getIdUser()).get();
        if(u != null){
            u.setNamaLengkap(user.getNamaLengkap());
            if(user.getPassword() != null || user.getPassword().trim().length() > 0){
                u.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            }
            u.setNoTelpon(user.getNoTelpon());
            userService.update(u);
        }
        return "redirect:main";
    }

    @RequestMapping(value = "/dashboard/user", method = RequestMethod.GET)
    public void getUserManagement(Pageable page, Model model, @ModelAttribute("currentUser")UserDetails userDetails){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        model.addAttribute("listUser", userService.getAllLogin(page));
    }
    @RequestMapping(value = "/dashboard/user/edit/{idUser}", method = RequestMethod.GET)
    public String getEditUserPage(@ModelAttribute("currentUser")UserDetails userDetails,Model model, @PathVariable(value = "idUser")String idUser){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        User u = userService.cariUSer(idUser);
        model.addAttribute("u", u);
        UserDto userDto = new UserDto();
        userDto.setEmail(u.getEmail());
        userDto.setNamaLengkap(u.getNamaLengkap());
        userDto.setIdUser(u.getIdUser());
        userDto.setSaldo(u.getSaldo());
        userDto.setRole(u.getRole());
        userDto.setNoTelpon(u.getNoTelpon());
        model.addAttribute("uDto", userDto);
        return "/dashboard/user/edit";
    }
    @RequestMapping(value = "/dashboard/user/edit/{idUser}",method = RequestMethod.POST)
    public String postUpdateUser(@ModelAttribute("currentUser")UserDetails userDetails, Model model, @PathVariable(value = "idUser")String idUser, UserDto userDto){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        User u = userService.cariUSer(idUser);
        if(userDto.getRole() == null){
            userDto.setRole(Roles.USER);
        }
        if(userDto.getSaldo() <= 0){
            userDto.setSaldo(new Double("1"));
        }
        u.setSaldo(Double.parseDouble(userDto.getSaldo()+""));
        u.setRole(userDto.getRole());
        userService.update(u);
        String url = "/dashboard/user/edit/"+u.getIdUser();
        return "redirect:"+url;
    }
    @RequestMapping(value = "/dashboard/setting", method = RequestMethod.GET)
    public void settingPage(@ModelAttribute("currentUser")UserDetails userDetails, Model model){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        UserDto uDto = new UserDto();
        UserDto userDto = new UserDto();
        userDto.setIdUser(user.getIdUser());
        userDto.setEmail(user.getEmail());
        userDto.setNamaLengkap(user.getNamaLengkap());
        userDto.setIdUser(user.getIdUser());
        userDto.setNoTelpon(user.getNoTelpon());
        model.addAttribute("uDto", userDto);
    }
    @RequestMapping(value = "/dashboard/setting", method = RequestMethod.POST)
    public void perbaruiUser(@ModelAttribute("currentUser")UserDetails userDetails, Model model,UserDto userDto){
        User u = userService.cariUSer(userDto.getIdUser());
        if(userDto.getNamaLengkap().length() > 0 || userDto.getIdUser() != null || !userDto.getIdUser().isEmpty()){
            u.setNamaLengkap(userDto.getNamaLengkap());
        }
        if(userDto.getNoTelpon().length() >0 || userDto.getNoTelpon() != null || !userDto.getNoTelpon().isEmpty() ){
            u.setNoTelpon(userDto.getNoTelpon());
        }
        if(userDto.getPassword().length() > 0 || userDto.getPassword() != null || !userDto.getPassword().isEmpty()){
            u.setPassword(new BCryptPasswordEncoder().encode(userDto.getPassword()));
        }
        userService.update(u);
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        UserDto uDto = new UserDto();
        uDto.setIdUser(user.getIdUser());
        uDto.setEmail(user.getEmail());
        uDto.setNamaLengkap(user.getNamaLengkap());
        uDto.setIdUser(user.getIdUser());
        uDto.setNoTelpon(user.getNoTelpon());
        model.addAttribute("uDto", uDto);
    }

}
