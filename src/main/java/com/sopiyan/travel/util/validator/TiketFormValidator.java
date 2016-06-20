package com.sopiyan.travel.util.validator;

import com.sopiyan.travel.model.dto.TiketDto;
import com.sopiyan.travel.model.entity.Rute;
import com.sopiyan.travel.model.entity.User;
import com.sopiyan.travel.service.services.RuteService;
import com.sopiyan.travel.service.services.TiketService;
import com.sopiyan.travel.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 * Created by Sopiyan on 17/06/2016.
 */

@Component
public class TiketFormValidator implements Validator{
    @Autowired
    private TiketService tiketService;
    @Autowired
    private RuteService ruteService;
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(TiketDto.class);
    }

    @Override
    public void validate(Object o, Errors errors) {
        TiketDto tiketDto = (TiketDto) o;
        validasiSaldo(tiketDto, errors);
        validasiTanggal(tiketDto, errors);

    }
    private void validasiSaldo(TiketDto tiketDto, Errors errors){
        if(tiketDto.getUser() == null){
            errors.reject("user.not_exist","Sepertinya Terjadi Kesalahan Silahkan Reload Page");
        }
        User u = userService.cariUSer(tiketDto.getUser().getIdUser());
        Rute r = ruteService.cariBerdasarkanId(tiketDto.getRute().getIdRute());
        if(u != null){
            if(r != null){
                if(u.getSaldo() < r.getHarga()){
                    errors.reject("saldo.tidak.cukup", "Mohon Maaf Saldo Anda Tidak Cukup");
                }
            }else{
                errors.reject("rute.not_exist","Sepertinya Terjadi Kesalahan Silahkan Reload Page");
            }
        }

    }
    private void validasiTanggal(TiketDto tiketDto, Errors errors){
        if(tiketDto.getTanggalBerangkat() == null){
            errors.reject("tanggal.empty","Tanggal Keberangkatan Tidak boleh kosong");
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, -1);
        Date tglKemaren = cal.getTime();
        if(tiketDto.getTanggalBerangkat().before(tglKemaren)){
            errors.reject("tanggal.kadaluars","Tanggal Keberangkatan Tidak boleh Kadaluarsa");
        }
    }
}
