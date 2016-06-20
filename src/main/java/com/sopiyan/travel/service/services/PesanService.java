package com.sopiyan.travel.service.services;

import com.sopiyan.travel.model.dto.PesanDto;
import com.sopiyan.travel.model.entity.Pesan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * Created by Sopiyan on 27/05/2016.
 */
public interface PesanService {
    Pesan simpan(PesanDto pesanDto);
    void hapus(Pesan pesan);
    Page<Pesan> getAllPesan(Pageable page);
    Page<Pesan> getPesanAtas(PageRequest page);
    Pesan cariPesanBerdasarkanId(String idPesan);
}
