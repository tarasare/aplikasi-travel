package com.sopiyan.travel.service.repository;

import com.sopiyan.travel.model.entity.Kota;
import com.sopiyan.travel.model.entity.Terminal;
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
public interface KotaRepository extends JpaRepository<Kota, String> {
//    @Query("select  k From Kota k where k.namaKota = :k")
    Kota findOneByNamaKota(String namaKota);

}
