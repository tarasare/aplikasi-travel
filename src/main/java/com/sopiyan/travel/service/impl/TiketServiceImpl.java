package com.sopiyan.travel.service.impl;

import com.sopiyan.travel.model.dto.TiketDto;
import com.sopiyan.travel.model.entity.Rute;
import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.model.entity.Tiket;
import com.sopiyan.travel.model.entity.User;
import com.sopiyan.travel.service.repository.TiketRepository;
import com.sopiyan.travel.service.services.RuteService;
import com.sopiyan.travel.service.services.TiketService;
import com.sopiyan.travel.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by Sopiyan on 01/06/2016.
 */
@Service
public class TiketServiceImpl implements TiketService {
    @Autowired
    private TiketRepository tiketRepository;
    @Autowired
    private RuteService ruteService;
    @Autowired
    private UserService userService;
    @Override
    public Tiket simpan(TiketDto tiketDto) {
        Tiket tiket = tiketDto.convertTo();
        User u = userService.cariUSer(tiketDto.getUser().getIdUser());
        u.setSaldo(u.getSaldo()-tiketDto.getHarga());
        return tiketRepository.save(tiket);
    }

    @Override
    public void update(Tiket tiket) {
        Tiket t = tiketRepository.findOne(tiket.getIdTiket());
        t.setHarga(tiket.getRute().getHarga());
        t.setTanggalBerangkat(tiket.getTanggalBerangkat());
        t.setUser(tiket.getUser());
        t.setRute(tiket.getRute());
        tiketRepository.save(t);
    }

    @Override
    public void hapus(Tiket tiket) {
        tiketRepository.delete(tiket);
    }

    @Override
    public Page<Tiket> getAllTiket(Pageable page) {
        return tiketRepository.findAll(page);
    }

    @Override
    public Collection<Tiket> cariBerdasarkanTerminalAsal(Terminal terminal) {
        return tiketRepository.findByRuteTerminalAsal(terminal);
    }

    @Override
    public Tiket cariTiket(String idTiket) {
        return tiketRepository.findOne(idTiket);
    }

    @Override
    public Collection<Tiket> cariBerdasarkanTerminalTujuan(Terminal terminal) {
        return tiketRepository.findByRuteTerminalTujuan(terminal);
    }
}
