package com.sopiyan.travel.service.impl;

import com.sopiyan.travel.model.dto.PercakapanDto;
import com.sopiyan.travel.model.entity.Percakapan;
import com.sopiyan.travel.service.repository.PercakapanRepository;
import com.sopiyan.travel.service.services.PercakapanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sopiyan on 27/05/2016.
 */
@Service
public class PercakapanServiceImpl implements PercakapanService{
    @Autowired
    private PercakapanRepository percakapanRepository;
    @Override
    public Percakapan simpan(PercakapanDto percakapanDto) {
        Percakapan percakapan = percakapanDto.convertToPercakapan();
        return percakapanRepository.save(percakapan);
    }

    @Override
    public void hapus(Percakapan percakapan) {
        percakapanRepository.delete(percakapan);
    }
}
