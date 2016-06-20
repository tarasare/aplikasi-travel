package com.sopiyan.travel.controller;

import com.sopiyan.travel.model.dto.RuteDto;
import com.sopiyan.travel.model.entity.Rute;
import com.sopiyan.travel.model.entity.User;
import com.sopiyan.travel.service.services.KotaService;
import com.sopiyan.travel.service.services.RuteService;
import com.sopiyan.travel.service.services.TerminalService;
import com.sopiyan.travel.service.services.UserService;
import com.sopiyan.travel.util.validator.RuteFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Sopiyan on 07/06/2016.
 */
@Controller
public class RuteController {
    @Autowired
    private RuteService ruteService;
    @Autowired
    private RuteFormValidator ruteFormValidator;
    @Autowired
    private KotaService kotaService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private UserService userService;
    @InitBinder(value = "ruteDto")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(ruteFormValidator);
    }
    @RequestMapping(value = "/dashboard/rute", method = RequestMethod.POST)
    public String simpanRute(@ModelAttribute("ruteDto")@Valid RuteDto ruteDto){
        Rute r = ruteService.simpan(ruteDto);
        return "redirect:/dashboard/rute";
    }
    @RequestMapping(value = "/dashboard/rute/edit", method = RequestMethod.POST)
    public String updateRute(Rute rute){
        ruteService.update(rute);
        return "redirect:/dashboard/rute";
    }
    @RequestMapping(value = "/dashboard/rute/edit", method = RequestMethod.GET)
    public void getViewRute(@RequestParam(required = true,name = "rute") String rute, @ModelAttribute("currentUser")UserDetails userDetails, Model model, Pageable page){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        Rute r = ruteService.cariBerdasarkanId(rute);
        model.addAttribute("rute", r);
        model.addAttribute("listTerminal",terminalService.getAllTerminal(page));
        System.out.println(r.getTerminalAsal().getNamaTerminal());
    }

    @RequestMapping(value = "/dashboard/api/rute", method = RequestMethod.GET)
    @ResponseBody
    public Page<Rute> getRuteJson(Pageable page){
        return ruteService.getAllRute(page);
    }

}
