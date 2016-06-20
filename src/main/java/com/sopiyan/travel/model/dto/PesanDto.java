package com.sopiyan.travel.model.dto;

import com.sopiyan.travel.model.entity.Percakapan;
import com.sopiyan.travel.model.entity.Pesan;
import com.sopiyan.travel.model.entity.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Sopiyan on 27/05/2016.
 */
public class PesanDto {
    @NotEmpty
    private String judulPesan;
    @NotNull
    private User user;
    private Collection<Percakapan> listPercakapan;

    public PesanDto(){
        this.listPercakapan = new ArrayList<>();
        this.user = new User();
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
    public Pesan convertToPesan(){
        Pesan pesan = new Pesan();
        pesan.setListPercakapan(this.getListPercakapan());
        pesan.setJudulPesan(this.getJudulPesan());
        pesan.setUser(this.getUser());
        return pesan;
    }
}
