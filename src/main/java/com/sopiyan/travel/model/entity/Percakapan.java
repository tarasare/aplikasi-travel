package com.sopiyan.travel.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Sopiyan on 27/05/2016.
 */
@Entity
@XmlRootElement
@Table(name = "percakapan_pesan")
public class Percakapan implements Serializable, Comparable<Percakapan>{
    @Id
    @Column(name = "id_percakapan",nullable = false, unique = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idPercakapan;
    @Column(name = "isi_percakapan")
    private String isiPercakapan;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private User user;
    @JoinColumn(name = "id_pesan", referencedColumnName = "id_pesan")
    @ManyToOne
    private Pesan pesan;
    @Column(name = "tanggal_percakapan", nullable = true)
    @Temporal(TemporalType.DATE)
    private Date tanggalPercakapan;
    @Column(name = "has_read", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean hasRead;

    public String getIdPercakapan() {
        return idPercakapan;
    }

    public void setIdPercakapan(String idPercakapan) {
        this.idPercakapan = idPercakapan;
    }

    public String getIsiPercakapan() {
        return isiPercakapan;
    }

    public void setIsiPercakapan(String isiPercakapan) {
        this.isiPercakapan = isiPercakapan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pesan getPesan() {
        return pesan;
    }

    public void setPesan(Pesan pesan) {
        this.pesan = pesan;
    }

    public Date getTanggalPercakapan() {
        return tanggalPercakapan;
    }

    public void setTanggalPercakapan(Date tanggalPercakapan) {
        this.tanggalPercakapan = tanggalPercakapan;
    }

    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }

    @Override
    public int compareTo(Percakapan percakapan) {
        return this.idPercakapan.compareTo(percakapan.getIdPercakapan());
    }
}
