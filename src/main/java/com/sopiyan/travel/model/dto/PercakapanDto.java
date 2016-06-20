package com.sopiyan.travel.model.dto;

import com.sopiyan.travel.model.entity.Percakapan;
import com.sopiyan.travel.model.entity.Pesan;
import com.sopiyan.travel.model.entity.User;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Sopiyan on 27/05/2016.
 */
public class PercakapanDto {

    @NotEmpty
    private String isiPercakapan;
    @NotNull
    private User user;
    @NotEmpty
    private Pesan pesan;
    @NotEmpty
    private Date tanggalPercakapan = new Date();
    @NotNull
    private boolean hasRead = false;

    public PercakapanDto() {
        this.tanggalPercakapan = new Date();
        this.hasRead = false;
        this.user = new User();
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
    public Percakapan convertToPercakapan(){
        Percakapan percakapan = new Percakapan();
        percakapan.setUser(this.getUser());
        percakapan.setHasRead(this.isHasRead());
        percakapan.setTanggalPercakapan(this.getTanggalPercakapan());
        percakapan.setIsiPercakapan(this.getIsiPercakapan());
        percakapan.setPesan(this.getPesan());
        return percakapan;
    }
}
