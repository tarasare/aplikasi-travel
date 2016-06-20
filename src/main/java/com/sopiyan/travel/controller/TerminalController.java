package com.sopiyan.travel.controller;

import com.sopiyan.travel.model.dto.TerminalDto;
import com.sopiyan.travel.model.entity.Kota;
import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.service.services.KotaService;
import com.sopiyan.travel.service.services.TerminalService;
import com.sopiyan.travel.util.validator.TerminalFormValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

/**
 * Created by Sopiyan on 04/06/2016.
 */
@RestController
public class TerminalController {
    @Autowired
    private TerminalService terminalService;
    @Autowired
    private TerminalFormValidator terminalFormValidator;
    @Autowired
    private KotaService kotaService;
    @InitBinder(value = "terminalDto")
    protected void initBinder(WebDataBinder binder) {
        binder.setValidator(terminalFormValidator);
    }
    @RequestMapping(value = "/dashboard/api/terminal", method = RequestMethod.POST)
    public ResponseEntity<Terminal> simpanTerminal(@RequestBody @Valid TerminalDto terminalDto){
        Terminal t = terminalService.simpan(terminalDto);
        return  new ResponseEntity<>(t, HttpStatus.CREATED);
    }
    @RequestMapping(value = "/dashboard/api/terminal", method = RequestMethod.GET)
    public Page<Terminal> tampilkanSemuaTerminal(Pageable page) {
        return terminalService.getAllTerminal(page);

    }
    @RequestMapping(value = "/dashboard/api/terminal/{id}", method = RequestMethod.DELETE)
    public void hapusTerminal(@PathVariable(value = "id") String id){
        Terminal terminal = terminalService.cariBerdasarkanId(id);
        if(terminal != null){
            terminalService.hapus(terminal);
        }
    }
    @RequestMapping(value = "/dashboard/api/terminal/berdasarkan", method = RequestMethod.GET)
    public Page<Terminal> getTerminalBerdasarkanKota(@RequestParam(value = "kota")String idKota, Pageable page){
        Kota k = kotaService.cariBerdasarkanID(idKota);
        return terminalService.cariBerdasarkanKota(k, page);

    }

}
