package com.sopiyan.travel.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sopiyan on 31/05/2016.
 */
@Entity
@XmlRootElement
@Table(name = "tiket")
public class Tiket implements Serializable, Comparable<Tiket>{
    @Id
    @Column(name = "id_tiket", nullable = false, unique = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idTiket;
    @Column(name = "tanggal_berangkat")
    @Temporal(TemporalType.DATE)
    private Date tanggalBerangkat;
    @JoinColumn(name = "id_rute", referencedColumnName = "id_rute")
    @ManyToOne
    private Rute rute;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private User user;
    @Column(name = "harga")
    private double harga;

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    public String getIdTiket() {
        return idTiket;
    }

    public void setIdTiket(String idTiket) {
        this.idTiket = idTiket;
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

    @Override
    public int compareTo(Tiket tiket) {
        return this.idTiket.compareTo(tiket.getIdTiket());
    }
}
