/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sopiyan.travel.model.dto;



import javax.validation.constraints.NotNull;

import com.sopiyan.travel.model.entity.Percakapan;
import com.sopiyan.travel.model.entity.Pesan;
import com.sopiyan.travel.model.entity.Tiket;
import com.sopiyan.travel.model.entity.User;
import com.sopiyan.travel.util.enumuration.Roles;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Sopiyan
 */
public class UserDto {
    private String idUser;
    @NotBlank
    @NotEmpty
    @NotNull
    @Email
    private String email;
    @NotBlank
    @NotEmpty
    @NotNull
    private String password;
    @NotBlank
    @NotEmpty
    @NotNull
    private String passwordRepeat;
    private boolean enabled;
    private Roles role;
    @NotBlank
    @NotEmpty
    @NotNull
    private String noTelpon;
    private String photo;
    @NotBlank
    @NotEmpty
    @NotNull
    private String namaLengkap;
    private Collection<Pesan> listPesan;
    private Collection<Percakapan> listPercakapan;
    private double saldo;
    private Collection<Tiket> listTiket;
    public UserDto() {
        this.enabled=true;
        this.role=Roles.USER;
        this.photo="/assets/img/default.jpg";
        this.listPesan = new ArrayList<>();
        this.listPercakapan = new ArrayList<>();
        this.saldo = new Double("1");
        this.listTiket = new ArrayList<>();
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
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

    public String getPasswordRepeat() {
        return passwordRepeat;
    }

    public void setPasswordRepeat(String passwordRepeat) {
        this.passwordRepeat = passwordRepeat;
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

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public User convertToUser(){
        User user = new User();
        user.setNamaLengkap(this.getNamaLengkap());
        user.setNoTelpon(this.getNoTelpon());
        user.setPhoto(this.getPhoto());
        user.setEnabled(this.isEnabled());
        user.setEmail(this.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(this.getPassword()));
        user.setRole(this.getRole());
        user.setListPercakapan(this.listPercakapan);
        user.setListPesan(this.listPesan);
        user.setSaldo(this.saldo);
        user.setListTiket(this.listTiket);
        return user;
    }
}
