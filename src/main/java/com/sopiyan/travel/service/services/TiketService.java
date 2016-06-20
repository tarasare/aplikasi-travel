package com.sopiyan.travel.service.services;

import com.sopiyan.travel.model.dto.TiketDto;
import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.model.entity.Tiket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

/**
 * Created by Sopiyan on 31/05/2016.
 */
public interface TiketService {
    Tiket simpan(TiketDto tiketDto);
    void update(Tiket tiket);
    void hapus(Tiket tiket);
    Page<Tiket> getAllTiket(Pageable page);
    Collection<Tiket> cariBerdasarkanTerminalAsal(Terminal terminal);
    Collection<Tiket> cariBerdasarkanTerminalTujuan(Terminal terminal);
    Tiket cariTiket(String idTiket);
}
