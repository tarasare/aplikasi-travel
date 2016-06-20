package com.sopiyan.travel.model.dto;

/**
 * Created by Sopiyan on 20/06/2016.
 */
public class PesanPercakapanDto {
    private PesanDto pesanDto;
    private PercakapanDto percakapanDto;

    public PesanDto getPesanDto() {
        return pesanDto;
    }

    public void setPesanDto(PesanDto pesanDto) {
        this.pesanDto = pesanDto;
    }

    public PercakapanDto getPercakapanDto() {
        return percakapanDto;
    }

    public void setPercakapanDto(PercakapanDto percakapanDto) {
        this.percakapanDto = percakapanDto;
    }
}
