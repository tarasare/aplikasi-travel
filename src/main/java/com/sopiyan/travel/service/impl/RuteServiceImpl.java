package com.sopiyan.travel.service.impl;

import com.sopiyan.travel.model.dto.RuteDto;
import com.sopiyan.travel.model.entity.Rute;
import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.service.repository.RuteRepository;
import com.sopiyan.travel.service.services.RuteService;
import com.sopiyan.travel.service.services.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Sopiyan on 01/06/2016.
 */
@Service
public class RuteServiceImpl implements RuteService {
    @Autowired
    private RuteRepository ruteRepository;
    @Autowired
    private TerminalService terminalService;
    @Override
    public Rute simpan(RuteDto ruteDto) {
        Terminal asal = terminalService.cariBerdasarkanId(ruteDto.getTerminalAsal().getIdTerminal());
        Terminal tujuan = terminalService.cariBerdasarkanId(ruteDto.getTerminalTujuan().getIdTerminal());
        Rute rute = ruteDto.convertToRute();
        rute.setTerminalAsal(asal);
        rute.setTerminalTujuan(tujuan);
        return ruteRepository.save(rute);
    }

    @Override
    public void update(Rute rute) {
        Rute r = ruteRepository.findOne(rute.getIdRute());
        if(r != null){
            r.setTerminalTujuan(rute.getTerminalTujuan());
            r.setTerminalAsal(rute.getTerminalAsal());
            r.setHarga(rute.getHarga());
            ruteRepository.save(r);
        }
    }

    @Override
    public void hapus(Rute rute) {
        ruteRepository.delete(rute);
    }

    @Override
    public Page<Rute> getAllRute(Pageable page) {
        return ruteRepository.findAll(page);
    }

    @Override
    public Rute cariBerdasarkanTerminalAsal(Terminal terminal) {
        return ruteRepository.findByTerminalAsal(terminal);
    }

    @Override
    public Rute cariBerdasarkanTerminalTujuan(Terminal terminal) {
        return ruteRepository.findByTerminalTujuan(terminal);
    }

    @Override
    public Rute cariBerdasarkanRuteAsalDanRuteTujuan(Terminal asal, Terminal tujuan) {
        return ruteRepository.findByTerminalAsalAndTerminalTujuan(asal,tujuan);
    }

    @Override
    public Rute cariBerdasarkanId(String id) {
        return ruteRepository.findOne(id);
    }

    @Override
    public Rute cariBerdasarkanRuteTujuanDanRuteAsal(Terminal tujuan, Terminal asal) {
        return ruteRepository.findByTerminalTujuanAndTerminalAsal(tujuan, asal);
    }
}
