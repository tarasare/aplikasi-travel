package com.sopiyan.travel.service.repository;

import com.sopiyan.travel.model.entity.Pesan;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sopiyan on 27/05/2016.
 */
@Repository
public interface PesanRepository extends PagingAndSortingRepository<Pesan, String> {

}
