package com.sopiyan.travel.controller;

import com.sopiyan.travel.model.dto.RuteDto;
import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.model.entity.User;
import com.sopiyan.travel.service.services.RuteService;
import com.sopiyan.travel.service.services.TerminalService;
import com.sopiyan.travel.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Sopiyan on 26/05/2016.
 */
@Controller
public class HomeController {
    private UserService userService;
    private TerminalService terminalService;
    private RuteService ruteService;

    @Autowired
    public HomeController(UserService userService, TerminalService terminalService, RuteService ruteService) {
        this.userService = userService;
        this.terminalService=terminalService;
        this.ruteService = ruteService;
    }

    @RequestMapping("/")
    public String getHome(){
        return "redirect:login";
    }
    @RequestMapping(value = "/dashboard/terminal", method = RequestMethod.GET)
    public void getTerminalPage(Pageable page, Model model, @ModelAttribute("currentUser")UserDetails userDetails) {
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }

    }
    @RequestMapping(value = "/dashboard/rute", method = RequestMethod.GET)
    public void getRutePage(Pageable page, Model model, @ModelAttribute("currentUser")UserDetails userDetails) {
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        RuteDto d = new RuteDto();
        model.addAttribute("ruteDto", d);
        model.addAttribute("listTerminal",terminalService.getAllTerminal(page));
        model.addAttribute("listRute", ruteService.getAllRute(page));
    }

}
