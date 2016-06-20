package com.sopiyan.travel.service.services;

import com.sopiyan.travel.model.dto.RuteDto;
import com.sopiyan.travel.model.entity.Rute;
import com.sopiyan.travel.model.entity.Terminal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;

/**
 * Created by Sopiyan on 31/05/2016.
 */
public interface RuteService {
    Rute simpan(RuteDto ruteDto);
    void update(Rute rute);
    Rute cariBerdasarkanId(String id);
    void hapus(Rute rute);
    Page<Rute> getAllRute(Pageable page);
    Rute cariBerdasarkanTerminalAsal(Terminal terminal);
    Rute cariBerdasarkanTerminalTujuan(Terminal terminal);
    Rute cariBerdasarkanRuteAsalDanRuteTujuan(Terminal asal, Terminal tujuan);
    Rute cariBerdasarkanRuteTujuanDanRuteAsal(Terminal tujuan, Terminal asal);

}
