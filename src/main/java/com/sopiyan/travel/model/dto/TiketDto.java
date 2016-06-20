package com.sopiyan.travel.model.dto;

import com.sopiyan.travel.model.entity.Rute;
import com.sopiyan.travel.model.entity.Tiket;
import com.sopiyan.travel.model.entity.User;

import javax.validation.constraints.Future;
import java.util.Date;

/**
 * Created by Sopiyan on 01/06/2016.
 */
public class TiketDto {
    @Future
    private Date tanggalBerangkat;
    private Rute rute;
    private User user;
    private double harga;

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public Date getTanggalBerangkat() {
        return tanggalBerangkat;
    }

    public void setTanggalBerangkat(Date tanggalBerangkat) {
        this.tanggalBerangkat = tanggalBerangkat;
    }

    public Rute getRute() {
        return rute;
    }

    public void setRute(Rute rute) {
        this.rute = rute;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Tiket convertTo(){
        Tiket tiket = new Tiket();
        tiket.setRute(this.getRute());
        tiket.setTanggalBerangkat(this.getTanggalBerangkat());
        tiket.setUser(this.getUser());
        tiket.setHarga(this.getRute().getHarga());
        return tiket;
    }
}
