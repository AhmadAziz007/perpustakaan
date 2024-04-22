package com.online.perpustakaan.dto.master;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MstBukuDTO {
    private Long bukuId;

    private String kodeBuku;

    private String judul;

    private String pengarang;

    private Integer jumlahStock;

    private Date tahunPenerbit;

    public Long getBukuId() {
        return bukuId;
    }

    public void setBukuId(Long bukuId) {
        this.bukuId = bukuId;
    }

    public String getKodeBuku() {
        return kodeBuku;
    }

    public void setKodeBuku(String kodeBuku) {
        this.kodeBuku = kodeBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public Integer getJumlahStock() {
        return jumlahStock;
    }

    public void setJumlahStock(Integer jumlahStock) {
        this.jumlahStock = jumlahStock;
    }

    public Date getTahunPenerbit() {
        return tahunPenerbit;
    }

    public void setTahunPenerbit(Date tahunPenerbit) {
        this.tahunPenerbit = tahunPenerbit;
    }
}
