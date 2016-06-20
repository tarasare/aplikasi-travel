package com.sopiyan.travel.model.dto;

import com.sopiyan.travel.model.entity.Kota;
import com.sopiyan.travel.model.entity.Terminal;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;

/**
 * Created by Sopiyan on 01/06/2016.
 */
public class TerminalDto {

    @NotEmpty
    private String namaTerminal;
    @NotEmpty
    @NotNull
    @Size(min = 2)
    private String idKota;
    public String getNamaTerminal() {
        return namaTerminal;
    }

    public void setNamaTerminal(String namaTerminal) {
        this.namaTerminal = namaTerminal;
    }

    public String getIdKota() {
        return idKota;
    }

    public void setIdKota(String idKota) {
        this.idKota = idKota;
    }

    public Terminal convertTo(){
        Terminal terminal = new Terminal();
        terminal.setNamaTerminal(this.getNamaTerminal());
        terminal.setListTerminalAsal(new ArrayList<>());
        terminal.setListTerminalTujuan(new ArrayList<>());
        return terminal;
    }
}
