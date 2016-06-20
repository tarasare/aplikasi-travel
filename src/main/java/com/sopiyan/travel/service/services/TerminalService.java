package com.sopiyan.travel.service.services;

import com.sopiyan.travel.model.dto.TerminalDto;
import com.sopiyan.travel.model.entity.Kota;
import com.sopiyan.travel.model.entity.Terminal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

/**
 * Created by Sopiyan on 31/05/2016.
 */
public interface TerminalService {
    Terminal simpan(TerminalDto terminalDto);
    void update(Terminal terminal);
    void hapus(Terminal terminal);
    Page<Terminal> getAllTerminal(Pageable pageable);
    Page<Terminal> cariBerdasarkanKota(Kota kota, Pageable pageable);
    Terminal cariBerdasarkanNamaTerminal(String namaTerminal);
    Terminal cariBerdasarkanId(String idTerminal);

}
