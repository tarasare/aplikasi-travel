package com.sopiyan.travel.controller;

import com.sopiyan.travel.model.dto.PercakapanDto;
import com.sopiyan.travel.model.dto.PesanDto;
import com.sopiyan.travel.model.dto.PesanPercakapanDto;
import com.sopiyan.travel.model.entity.Percakapan;
import com.sopiyan.travel.model.entity.Pesan;
import com.sopiyan.travel.model.entity.User;
import com.sopiyan.travel.service.services.PercakapanService;
import com.sopiyan.travel.service.services.PesanService;
import com.sopiyan.travel.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Sopiyan on 20/06/2016.
 */
@Controller
public class PesanController {

    @Autowired
    private PesanService pesanService;
    @Autowired
    private PercakapanService percakapanService;
    @Autowired
    private UserService userService;
    @RequestMapping(value = "/pesan", method = RequestMethod.POST)
    public String simpanPesan(PesanPercakapanDto pesanPercakapanDto, @ModelAttribute("currentUser")UserDetails userDetails){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        PesanDto pesanDto = pesanPercakapanDto.getPesanDto();
        PercakapanDto percakapanDto = pesanPercakapanDto.getPercakapanDto();
        pesanDto.setUser(user);
        pesanDto.setListPercakapan(new ArrayList<>());
        Pesan p = pesanService.simpan(pesanDto);
        percakapanDto.setUser(user);
        percakapanDto.setHasRead(false);
        percakapanDto.setPesan(p);
        percakapanDto.setTanggalPercakapan(new Date());
        Percakapan percakapan = percakapanService.simpan(percakapanDto);
        return "redirect:main";
    }
    @RequestMapping(value = "/pesan", method = RequestMethod.GET)
    public void getPagePesan(@ModelAttribute("currentUser")UserDetails userDetails, Model model){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        PesanPercakapanDto pesanPercakapanDto = new PesanPercakapanDto();
        PesanDto pesanDto = new PesanDto();
        PercakapanDto percakapanDto = new PercakapanDto();
        pesanPercakapanDto.setPesanDto(pesanDto);
        pesanPercakapanDto.setPercakapanDto(percakapanDto);
        model.addAttribute("pesanPercakapanDto", pesanPercakapanDto);

    }

    @RequestMapping(value = "/pesan/{idPesan}", method = RequestMethod.GET)
    public String lihatPercakapan(@ModelAttribute("currentUser")UserDetails userDetails, Model model, @PathVariable(value = "idPesan")String idPesan){
        Pesan p = pesanService.cariPesanBerdasarkanId(idPesan);
        model.addAttribute("pesan", p);
        model.addAttribute("listPercakapan", p.getListPercakapan());
        System.out.println(p.getListPercakapan());
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        return "percakapan";
    }
}
