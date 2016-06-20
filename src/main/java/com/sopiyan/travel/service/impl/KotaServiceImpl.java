package com.sopiyan.travel.service.impl;

import com.sopiyan.travel.model.dto.KotaDto;
import com.sopiyan.travel.model.entity.Kota;
import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.service.repository.KotaRepository;
import com.sopiyan.travel.service.services.KotaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Sopiyan on 31/05/2016.
 */
@Service
public class KotaServiceImpl implements KotaService{
    @Autowired
    private KotaRepository kotaRepository;
    @Override
    public Kota simpan(KotaDto kotaDto) {
        Kota kota = kotaDto.convertToKota();
        return kotaRepository.save(kota);
    }

    @Override
    public void update(Kota kota) {
        Kota k = kotaRepository.findOne(kota.getIdKota());
        k.setNamaKota(kota.getNamaKota());
        k.getListTerminal().addAll(kota.getListTerminal());
        kotaRepository.save(k);
    }

    @Override
    public void hapus(Kota kota) {
        Kota k = kotaRepository.findOne(kota.getIdKota());
        if(k != null){
            kotaRepository.delete(k);
        }
    }

    @Override
    public void hapus(String id) {
        Kota k = kotaRepository.findOne(id);
        if(k != null){
            kotaRepository.delete(k);
        }
    }

    @Override
    public Kota cariBerdasarkanID(String id) {
        return kotaRepository.findOne(id);
    }

    @Override
    public Page<Kota> getAllKota(Pageable pageable) {
        return kotaRepository.findAll(pageable);
    }


    @Override
    public Kota cariBerdasarkanNamaKota(String namaKota) {
        return kotaRepository.findOneByNamaKota(namaKota);
    }
}
