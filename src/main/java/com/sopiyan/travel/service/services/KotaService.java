package com.sopiyan.travel.service.services;

import com.sopiyan.travel.model.dto.KotaDto;
import com.sopiyan.travel.model.entity.Kota;
import com.sopiyan.travel.model.entity.Terminal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.Collection;

/**
 * Created by Sopiyan on 31/05/2016.
 */
public interface KotaService {
    Kota simpan(KotaDto kotaDto);
    void update(Kota kota);
    void hapus(Kota kota);
    void hapus(String id);
    Page<Kota> getAllKota(Pageable pageable);
    Kota cariBerdasarkanNamaKota(String namaKota);
    Kota cariBerdasarkanID(String id);

}
