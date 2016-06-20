package com.sopiyan.travel.controller;

import com.sopiyan.travel.model.dto.KotaDto;
import com.sopiyan.travel.model.entity.Kota;
import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.model.entity.User;
import com.sopiyan.travel.service.services.KotaService;
import com.sopiyan.travel.service.services.UserService;
import com.sopiyan.travel.util.validator.KotaFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * Created by Sopiyan on 31/05/2016.
 */
@Controller
public class KotaController {
    @Autowired
    private KotaService kotaService;
    @Autowired
    private KotaFormValidator kotaFormValidator;
    @Autowired
    private UserService userService;

    @InitBinder("kotaDto")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(kotaFormValidator);
    }

    @RequestMapping(value = "/dashboard/kota", method = RequestMethod.GET)
    public void tampilkanSemuaKota(Pageable page, Model model,@ModelAttribute("currentUser")UserDetails userDetails) {
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        model.addAttribute("listKota", kotaService.getAllKota(page));
        model.addAttribute("kotaDto", new KotaDto());
    }
    @RequestMapping(value = "/api/kota", method = RequestMethod.GET)
    @ResponseBody
    public Page<Kota> tampilkanSemuaKota(Pageable page) {
        return kotaService.getAllKota(page);
    }

    @RequestMapping(value = "/dashboard/kota/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void hapusKota(@PathVariable("id") String id) {
        kotaService.hapus(id);
    }

    @RequestMapping(value = "/dashboard/kota", method = RequestMethod.POST)
    public String simpanKota(@ModelAttribute("kotaDto")@Valid KotaDto kotaDto, @ModelAttribute("currentUser")UserDetails userDetails, Model model, BindingResult result) {
        kotaService.simpan(kotaDto);
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        model.addAttribute("kotaDto", kotaDto);
        model.addAttribute("error", result.hasErrors());
        return "dashboard/kota";
    }
    @RequestMapping(value = "/api/kota", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Kota> simpanKota(@RequestBody String namaKota, Errors errors) {
        KotaDto kotaDto = new KotaDto();
        kotaDto.setNamaKota(namaKota);
        kotaFormValidator.validate(kotaDto, errors);
        Kota k = kotaService.simpan(kotaDto);

        return new ResponseEntity<>(k, HttpStatus.CREATED);
    }
}
