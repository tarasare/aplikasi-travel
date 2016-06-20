package com.sopiyan.travel.model.entity;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.GenericGenerator;

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
@Table(name = "terminal")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idTerminal")
public class Terminal implements Serializable, Comparable<Terminal> {
    @Id
    @Column(name = "id_terminal", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idTerminal;
    @Column(name = "nama_terminal", nullable = false, unique = true)
    private String namaTerminal;
    @JoinColumn(name = "id_kota",referencedColumnName = "id_kota", nullable = false)
    @ManyToOne()
    @JsonBackReference
    private Kota kota;
    @OneToMany(mappedBy = "terminalAsal", cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlTransient
    @JsonBackReference
    private Collection<Rute> listTerminalAsal;
    @OneToMany(mappedBy = "terminalTujuan", cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlTransient
    @JsonBackReference
    private Collection<Rute> listTerminalTujuan;

    public Collection<Rute> getListTerminalAsal() {
        return listTerminalAsal;
    }

    public void setListTerminalAsal(Collection<Rute> listTerminalAsal) {
        this.listTerminalAsal = listTerminalAsal;
    }

    public Collection<Rute> getListTerminalTujuan() {
        return listTerminalTujuan;
    }

    public void setListTerminalTujuan(Collection<Rute> listTerminalTujuan) {
        this.listTerminalTujuan = listTerminalTujuan;
    }

    public Kota getKota() {
        return kota;
    }

    public void setKota(Kota kota) {
        this.kota = kota;
    }

    public String getIdTerminal() {
        return idTerminal;
    }

    public void setIdTerminal(String idTerminal) {
        this.idTerminal = idTerminal;
    }

    public String getNamaTerminal() {
        return namaTerminal;
    }

    public void setNamaTerminal(String namaTerminal) {
        this.namaTerminal = namaTerminal;
    }

    @Override
    public int compareTo(Terminal terminal) {
        return this.idTerminal.compareTo(terminal.getIdTerminal());
    }
}
