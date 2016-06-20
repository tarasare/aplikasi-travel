package com.sopiyan.travel.service.repository;

import com.sopiyan.travel.model.entity.Kota;
import com.sopiyan.travel.model.entity.Terminal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

/**
 * Created by Sopiyan on 01/06/2016.
 */
@Repository
public interface TerminalRepository extends JpaRepository<Terminal, String> {
    Page<Terminal> findByKota(Kota kota, Pageable pageable);
    Terminal findByNamaTerminal(String namaTerminal);

}


