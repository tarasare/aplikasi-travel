package com.sopiyan.travel.model.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.util.Collection;

/**
 * Created by Sopiyan on 27/05/2016.
 */
@Entity
@XmlRootElement
@Table(name = "user_pesan")
public class Pesan implements Serializable, Comparable<Pesan>{
    @Id
    @Column(name = "id_pesan",nullable = false, unique = true)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idPesan;
    @Basic(optional = false)
    @Column(name = "judul_pesan", nullable = false)
    private String judulPesan;
    @JoinColumn(name = "id_user", referencedColumnName = "id_user")
    @ManyToOne
    private User user;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @XmlTransient
    private Collection<Percakapan> listPercakapan;

    public String getIdPesan() {
        return idPesan;
    }

    public void setIdPesan(String idPesan) {
        this.idPesan = idPesan;
    }

    public String getJudulPesan() {
        return judulPesan;
    }

    public void setJudulPesan(String judulPesan) {
        this.judulPesan = judulPesan;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Collection<Percakapan> getListPercakapan() {
        return listPercakapan;
    }

    public void setListPercakapan(Collection<Percakapan> listPercakapan) {
        this.listPercakapan = listPercakapan;
    }

    @Override
    public int compareTo(Pesan pesan) {
        return this.idPesan.compareTo(pesan.getIdPesan());
    }
}
