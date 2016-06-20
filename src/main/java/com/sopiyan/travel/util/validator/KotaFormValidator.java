package com.sopiyan.travel.util.validator;

import com.sopiyan.travel.model.dto.KotaDto;
import com.sopiyan.travel.service.services.KotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Created by Sopiyan on 02/06/2016.
 */
@Component
public class KotaFormValidator implements Validator {
    @Autowired
    private KotaService kotaService;
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(KotaDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        KotaDto kotaDto = (KotaDto) target;
        validasiKetersedianKota(kotaDto, errors);
    }
    private void validasiKetersedianKota(KotaDto kotaDto, Errors errors){
        if(kotaService.cariBerdasarkanNamaKota(kotaDto.getNamaKota())!= null){
            errors.reject("kota.exists", String.format("Kota %s sudah tersedia",kotaDto.getNamaKota()));
        }
        if(kotaDto.getNamaKota() == null || kotaDto.getNamaKota().length() == 0){
            errors.reject("kota.nama", "Nama Kota Tidak Boleh Kosong");
        }

    }
}
