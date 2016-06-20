/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sopiyan.travel.model.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.sopiyan.travel.model.dto.PesanDto;
import com.sopiyan.travel.util.enumuration.Roles;
import org.hibernate.annotations.GenericGenerator;

/**
 *
 * @author Sopiyan
 */
@Entity
@XmlRootElement
@Table(name = "user_login")
public class User implements Serializable{
    @Id
    @Basic(optional = false)
    @Column(name = "id_user", unique = true, nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String idUser;
    @Basic(optional = false)
    @Column(name = "email_login", nullable = false, unique = true, length = 40)
    private String email;
    @Column(name = "password_login", nullable = false)
    private String password;
    @Column(name = "enabled", nullable = false, columnDefinition = "TINYINT(1)")
    private boolean enabled;
    @Basic
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Roles role;
    @Column(name = "telpon", length = 20)
    private String noTelpon;
    @Column(name = "photo")
    private String photo;
    @Column(name = "nama_lengkap")
    private String namaLengkap;
    @OneToMany(mappedBy = "user")
    @XmlTransient
    private Collection<Pesan> listPesan;
    @OneToMany(mappedBy = "user")
    @XmlTransient
    private Collection<Percakapan> listPercakapan;
    @Column(name = "saldo")
    private double saldo;
    @OneToMany(orphanRemoval = true, mappedBy = "user")
    @XmlTransient
    private Collection<Tiket> listTiket;


    public Collection<Tiket> getListTiket() {
        return listTiket;
    }

    public void setListTiket(Collection<Tiket> listTiket) {
        this.listTiket = listTiket;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Collection<Pesan> getListPesan() {
        return listPesan;
    }

    public void setListPesan(Collection<Pesan> listPesan) {
        this.listPesan = listPesan;
    }

    public Collection<Percakapan> getListPercakapan() {
        return listPercakapan;
    }

    public void setListPercakapan(Collection<Percakapan> listPercakapan) {
        this.listPercakapan = listPercakapan;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getNoTelpon() {
        return noTelpon;
    }

    public void setNoTelpon(String noTelpon) {
        this.noTelpon = noTelpon;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
