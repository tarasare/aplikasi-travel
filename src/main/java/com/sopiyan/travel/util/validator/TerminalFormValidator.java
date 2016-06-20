package com.sopiyan.travel.util.validator;

import com.sopiyan.travel.model.dto.TerminalDto;
import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.service.services.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Sopiyan on 04/06/2016.
 */
@Component
public class TerminalFormValidator implements Validator {
    @Autowired
    private TerminalService terminalService;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(TerminalDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        TerminalDto terminalDto = (TerminalDto) target;
        validasiKetersediaanTerminal(terminalDto, errors);
        validasiKotaTerminal(terminalDto, errors);
    }
    private void validasiKetersediaanTerminal(TerminalDto terminalDto, Errors errors){
        Terminal t = terminalService.cariBerdasarkanNamaTerminal(terminalDto.getNamaTerminal());
        if(t != null){
            errors.reject("Terminal.exist", String.format("Terminal %s Sudah tersedia",t.getNamaTerminal()));
        }
    }
    private void validasiKotaTerminal(TerminalDto terminalDto, Errors errors){
        if(terminalDto.getIdKota() == null){
            errors.reject("Terminal.kota.not.exist", String.format("Silahkan Input Kota Terminal %s ", terminalDto.getNamaTerminal()));
        }
        if(terminalDto.getNamaTerminal() == null || terminalDto.getNamaTerminal().length() == 0){
            errors.reject("Terminal.nama", String.format("Silahkan Input Nama Terminal %s ", terminalDto.getNamaTerminal()));
        }
    }
}
