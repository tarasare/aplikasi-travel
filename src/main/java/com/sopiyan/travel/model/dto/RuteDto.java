package com.sopiyan.travel.model.dto;

import com.sopiyan.travel.model.entity.Rute;
import com.sopiyan.travel.model.entity.Terminal;
import com.sopiyan.travel.model.entity.Tiket;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sopiyan on 31/05/2016.
 */
public class RuteDto {
    @NotNull
    private Terminal terminalAsal;
    @NotNull
    private Terminal terminalTujuan;
    @NotNull
    private double harga;

    private Collection<Tiket> listTiket;

    public RuteDto() {
        this.listTiket = new ArrayList<>();
    }

    public Terminal getTerminalAsal() {
        return terminalAsal;
    }

    public void setTerminalAsal(Terminal terminalAsal) {
        this.terminalAsal = terminalAsal;
    }

    public Terminal getTerminalTujuan() {
        return terminalTujuan;
    }

    public void setTerminalTujuan(Terminal terminalTujuan) {
        this.terminalTujuan = terminalTujuan;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public Collection<Tiket> getListTiket() {
        return listTiket;
    }

    public void setListTiket(Collection<Tiket> listTiket) {
        this.listTiket = listTiket;
    }
    public Rute convertToRute(){
        Rute rute = new Rute();
        rute.setHarga(Double.parseDouble(this.getHarga()+""));
        rute.setListTiket(this.listTiket);
        rute.setTerminalAsal(this.getTerminalAsal());
        rute.setTerminalTujuan(this.getTerminalTujuan());
        return rute;
    }
}
