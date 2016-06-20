package com.sopiyan.travel.service.repository;

import com.sopiyan.travel.model.entity.Rute;
import com.sopiyan.travel.model.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sopiyan on 31/05/2016.
 */
@Repository
public interface RuteRepository extends PagingAndSortingRepository<Rute, String> {
    Rute findByTerminalAsal(Terminal terminal);
    Rute findByTerminalTujuan(Terminal terminal);
    Rute findByTerminalAsalAndTerminalTujuan(Terminal asal, Terminal tujuan);
    Rute findByTerminalTujuanAndTerminalAsal(Terminal tujuan, Terminal asal);
}
