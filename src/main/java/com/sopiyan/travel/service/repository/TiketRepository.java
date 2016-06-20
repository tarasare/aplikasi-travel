package com.sopiyan.travel.service.repository;

import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.model.entity.Tiket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Sopiyan on 31/05/2016.
 */
@Repository
public interface TiketRepository extends PagingAndSortingRepository<Tiket, String> {
/*    @Query("SELECT t FROM Tiket t where  t.rute.terminalAsal = :t")
     Collection<Tiket> cariBerdasarkanTerminalAsal(@Param("t")Terminal terminal);
    @Query("SELECT t FROM Tiket t where  t.rute.terminalTujuan = :t")
     Collection<Tiket> cariBerdasarkanTerminalTujuan(@Param("t")Terminal terminal);*/
    Collection<Tiket> findByRuteTerminalAsal(Terminal terminal);
    Collection<Tiket> findByRuteTerminalTujuan(Terminal terminal);
}
