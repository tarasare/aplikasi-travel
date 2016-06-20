package com.sopiyan.travel.model.entity;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Sopiyan on 31/05/2016.
 */
@Entity
@XmlRootElement
@Table(name = "kota")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idKota")
public class Kota implements Serializable, Comparable<Kota> {
    @Id
    @Column(name = "id_kota", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idKota;
    @Basic(optional = false)
    @Column(name = "nama_kota", length = 100, nullable = false, unique = true)
    private String namaKota;
    @OneToMany(mappedBy = "kota", orphanRemoval = true)
    @XmlTransient
    @JsonManagedReference
    private Collection<Terminal> listTerminal;

    public Collection<Terminal> getListTerminal() {
        return listTerminal;
    }

    public void setListTerminal(Collection<Terminal> listTerminal) {
        this.listTerminal = listTerminal;
    }

    public String getIdKota() {
        return idKota;
    }

    public void setIdKota(String idKota) {
        this.idKota = idKota;
    }

    public String getNamaKota() {
        return namaKota;
    }

    public void setNamaKota(String namaKota) {
        this.namaKota = namaKota;
    }

    @Override
    public int compareTo(Kota kota) {
        return this.idKota.compareTo(kota.getIdKota());
    }
}
