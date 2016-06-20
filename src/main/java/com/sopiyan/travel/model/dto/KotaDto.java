package com.sopiyan.travel.model.dto;

import com.sopiyan.travel.model.entity.Kota;
import com.sopiyan.travel.model.entity.Terminal;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sopiyan on 31/05/2016.
 */
public class KotaDto {
    private String namaKota;
    private Collection<Terminal> listTerminal;

    public KotaDto() {
        this.listTerminal = new ArrayList<>();
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    public Collection<Terminal> getListTerminal() {
        return listTerminal;
    }

    public void setListTerminal(Collection<Terminal> listTerminal) {
        this.listTerminal = listTerminal;
    }
    public Kota convertToKota(){
        Kota kota = new Kota();
        kota.setNamaKota(this.getNamaKota());
        kota.setListTerminal(this.listTerminal);
        return kota;
    }
}
