package com.sopiyan.travel.service.services;

import com.sopiyan.travel.model.dto.PercakapanDto;
import com.sopiyan.travel.model.entity.Percakapan;

/**
 * Created by Sopiyan on 27/05/2016.
 */
public interface PercakapanService {
    Percakapan simpan(PercakapanDto percakapanDto);
    void hapus(Percakapan percakapan);
}
