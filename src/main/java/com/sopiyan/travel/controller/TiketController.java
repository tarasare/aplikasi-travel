package com.sopiyan.travel.controller;

import com.sopiyan.travel.model.dto.RuteDto;
import com.sopiyan.travel.model.dto.TiketDto;
import com.sopiyan.travel.model.entity.*;
import com.sopiyan.travel.service.services.*;
import com.sopiyan.travel.util.validator.TiketFormValidator;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Sopiyan on 16/06/2016.
 */
@Controller
public class TiketController {
    @Autowired
    private TiketService tiketService;
    @Autowired
    private UserService userService;
    @Autowired
    private KotaService kotaService;
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private RuteService ruteService;
    @Autowired
    private TiketFormValidator tiketFormValidator;

    @InitBinder(value = "tiketDto")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(tiketFormValidator);
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        df.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(df,false));
    }



    @RequestMapping(value = "/tiket", method = RequestMethod.GET)
    public void getTiketPage(Pageable page, Model model, @ModelAttribute("currentUser")UserDetails userDetails){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        model.addAttribute("rute", new Rute());
    }
    @RequestMapping(value = "/print", method = RequestMethod.GET)
    public String printTiket(@RequestParam(name = "tiket",required = true)String idTiket, Model model){
        Tiket t = tiketService.cariTiket(idTiket);
        if(t == null){
            return "redirect:main";
        }
        model.addAttribute("tiket", t);
        return "print";
    }
    @RequestMapping(value = "/tiket", method = RequestMethod.POST)
    public String simpanPemesanTiket(@ModelAttribute("tiketDto")@Valid TiketDto tiketDto, BindingResult result, @ModelAttribute("currentUser")UserDetails userDetails,Model model){
        if(result.hasErrors()){
            System.out.println(result);
            return "redirect:/tiket/"+tiketDto.getRute().getIdRute();
        }
        tiketService.simpan(tiketDto);
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        return "alltiket";
    }
    @RequestMapping(value = "/tiket/semua", method = RequestMethod.GET)
    public String ihatSemuaTiket(@ModelAttribute("currentUser")UserDetails userDetails,Model model){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        return "alltiket";
    }
    @RequestMapping(value = "/api/tiket/terminal", method = RequestMethod.GET)
    @ResponseBody
    public Page<Terminal> getListTeminalByKota(@RequestParam(name = "idKota")String idKota, Pageable pageable){
        Kota k = kotaService.cariBerdasarkanID(idKota);
        return terminalService.cariBerdasarkanKota(k, pageable);
    }
    @RequestMapping(value = "/api/tiket/rute", method = RequestMethod.GET)
    @ResponseBody
    public Rute cariRute(@RequestParam(name = "terminalAsal")String terminalAsal, @RequestParam(name = "terminalTujuan")String terminalTujuan ){
        Terminal asal = terminalService.cariBerdasarkanId(terminalAsal);
        Terminal tujuan = terminalService.cariBerdasarkanId(terminalTujuan);
        Rute r = ruteService.cariBerdasarkanRuteAsalDanRuteTujuan(asal, tujuan);
        return r;
    }
    @RequestMapping(value = "/api/tiket/kota", method = RequestMethod.GET)
    @ResponseBody
    public Kota cariKota(@RequestParam(name = "idTerminal")String idTerminal){
        Terminal t = terminalService.cariBerdasarkanId(idTerminal);
        Kota k = t.getKota();
        return k;
    }
    @RequestMapping(value = "/tiket/{id}", method = RequestMethod.GET)
    public String prosesPembelian(Pageable page, Model model, @ModelAttribute("currentUser")UserDetails userDetails,@PathVariable(value = "id") String idRute){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        Rute r = ruteService.cariBerdasarkanId(idRute);
        TiketDto tiketDto = new TiketDto();
        tiketDto.setUser(user);
        tiketDto.setHarga(r.getHarga());
        tiketDto.setRute(r);
        tiketDto.setTanggalBerangkat(new Date());
        model.addAttribute("rute", r);
        model.addAttribute("tiketDto", tiketDto);
        model.addAttribute("tanggalPesan", new Date());
        boolean cukup = user.getSaldo() > r.getHarga();
        model.addAttribute("cukup",cukup);
        return "proses";
    }
    @RequestMapping(value = "/dashboard/tiket", method = RequestMethod.GET)
    public void getAllTiket(Pageable page, @ModelAttribute("currentUser")UserDetails userDetails, Model model){
        User user = userService.getLoginByEmail(userDetails.getUsername()).get();
        if(user != null){
            model.addAttribute("user", user);
        }
        model.addAttribute("listTiket", tiketService.getAllTiket(page));
    }
    @RequestMapping(value = "/dashboard/tiket/hapus/{idTiket}", method = RequestMethod.GET)
    public String deleteTiket(@PathVariable(value = "idTiket") String idTiket){
        Tiket t = tiketService.cariTiket(idTiket);
        tiketService.hapus(t);
        return "redirect:/dashboard/tiket";
    }
}
