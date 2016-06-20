package com.sopiyan.travel.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.web.servlet.View;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Sopiyan on 31/05/2016.
 */
@Entity
@XmlRootElement
@Table(name = "rute")
public class Rute implements Serializable, Comparable<Rute>{
    @Id
    @Column(name = "id_rute", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idRute;
    @JoinColumn(name = "id_terminal_asal", referencedColumnName = "id_terminal")
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Terminal terminalAsal;
    @JoinColumn(name = "id_terminal_tujuan", referencedColumnName = "id_terminal")
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Terminal terminalTujuan;
    @Column(name = "harga", nullable = false)
    private double harga;
    @OneToMany(mappedBy = "rute")
    @XmlTransient
    @JsonIgnore
    private Collection<Tiket> listTiket;

    public Collection<Tiket> getListTiket() {
        return listTiket;
    }

    public void setListTiket(Collection<Tiket> listTiket) {
        this.listTiket = listTiket;
    }

    public String getIdRute() {
        return idRute;
    }

    public void setIdRute(String idRute) {
        this.idRute = idRute;
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

    @Override
    public int compareTo(Rute rute) {
        return this.idRute.compareTo(rute.getIdRute());
    }
}
