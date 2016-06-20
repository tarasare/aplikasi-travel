package com.sopiyan.travel.util.validator;

import com.sopiyan.travel.model.dto.RuteDto;
import com.sopiyan.travel.service.services.RuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Sopiyan on 07/06/2016.
 */
@Component
public class RuteFormValidator implements Validator {
    @Autowired
    private RuteService ruteService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(RuteDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RuteDto ruteDto = (RuteDto) target;
        validasiRuteDto(ruteDto, errors);
    }
    private void validasiRuteDto(RuteDto ruteDto, Errors errors){
        if(ruteService.cariBerdasarkanRuteAsalDanRuteTujuan(ruteDto.getTerminalAsal(), ruteDto.getTerminalTujuan()) != null){
            errors.reject("rute.exist","Rute Dengan Terminal Asal " + ruteDto.getTerminalAsal().getNamaTerminal() + " Dengan Tujuan Terminal "+ ruteDto.getTerminalTujuan().getNamaTerminal() +" Sudah Tersedia");
        }
        if(ruteService.cariBerdasarkanRuteTujuanDanRuteAsal(ruteDto.getTerminalTujuan(), ruteDto.getTerminalAsal())!= null){
            errors.reject("rute.exist","Rute Dengan Terminal Asal " + ruteDto.getTerminalAsal().getNamaTerminal() + " Dengan Tujuan Terminal "+ ruteDto.getTerminalTujuan().getNamaTerminal() +" Sudah Tersedia");
        }

        double d = Double.parseDouble(ruteDto.getHarga()+"");
        if(d <= 0){
            errors.reject("rute.harga","Harga Tidak Boleh Kosong");
        }
    }
}
