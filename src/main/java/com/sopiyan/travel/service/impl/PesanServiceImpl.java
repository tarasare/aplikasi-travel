package com.sopiyan.travel.service.impl;

import com.sopiyan.travel.model.dto.PesanDto;
import com.sopiyan.travel.model.entity.Pesan;
import com.sopiyan.travel.service.repository.PesanRepository;
import com.sopiyan.travel.service.services.PesanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Created by Sopiyan on 27/05/2016.
 */
@Service
public class PesanServiceImpl implements PesanService {
    @Autowired
    private PesanRepository pesanRepository;
    @Override
    public Pesan simpan(PesanDto pesanDto) {
        Pesan pesan = pesanDto.convertToPesan();
        return pesanRepository.save(pesan);
    }

    @Override
    public Page<Pesan> getAllPesan(Pageable page) {
        return pesanRepository.findAll(page);
    }

    @Override
    public Page<Pesan> getPesanAtas(PageRequest page) {
        return pesanRepository.findAll(page);
    }

    @Override
    public Pesan cariPesanBerdasarkanId(String idPesan) {
        return pesanRepository.findOne(idPesan);
    }

    @Override
    public void hapus(Pesan pesan) {
        pesanRepository.delete(pesan);
    }
}
