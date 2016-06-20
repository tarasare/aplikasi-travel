package com.sopiyan.travel.service.impl;

import com.sopiyan.travel.model.dto.TerminalDto;
import com.sopiyan.travel.model.entity.Kota;
import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.service.repository.TerminalRepository;
import com.sopiyan.travel.service.services.KotaService;
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
public class TerminalServiceImpl implements TerminalService {
    @Autowired
    private TerminalRepository terminalRepository;
    @Autowired
    private KotaService kotaService;
    @Override
    public Terminal simpan(TerminalDto terminalDto) {
        Terminal terminal = terminalDto.convertTo();
        Kota kota = kotaService.cariBerdasarkanID(terminalDto.getIdKota());
        terminal.setKota(kota);
        return terminalRepository.save(terminal);
    }

    @Override
    public void update(Terminal terminal) {
        Terminal t = terminalRepository.findOne(terminal.getIdTerminal());
        t.setKota(terminal.getKota());
        t.setNamaTerminal(terminal.getNamaTerminal());
        terminalRepository.save(t);
    }

    @Override
    public void hapus(Terminal terminal) {
        Kota kota = terminal.getKota();
        kota.getListTerminal().remove(terminal);
        terminalRepository.delete(terminal);

    }

    @Override
    public Terminal cariBerdasarkanNamaTerminal(String namaTerminal) {
        return terminalRepository.findByNamaTerminal(namaTerminal);
    }

    @Override
    public Page<Terminal> getAllTerminal(Pageable pageable) {
        return terminalRepository.findAll(pageable);
    }

    @Override
    public Page<Terminal> cariBerdasarkanKota(Kota kota, Pageable pageable) {
        return terminalRepository.findByKota(kota, pageable);
    }

    @Override
    public Terminal cariBerdasarkanId(String idTerminal) {
        return terminalRepository.findOne(idTerminal);
    }
}
