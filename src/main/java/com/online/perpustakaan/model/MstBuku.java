package com.online.perpustakaan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "mst_buku", schema = "public")
public class MstBuku {
    @Id
    @SequenceGenerator(name = "mst_buku_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_buku_seq")
    @Column(name = "buku_id")
    private Long bukuId;

    @Column(name = "kode_buku", length = 20)
    private String kodeBuku;

    @Column(name = "judul", length = 20)
    private String judul;

    @Column(name = "pengarang", length = 20)
    private String pengarang;

    @Column(name = "jumlah_stock")
    private Integer jumlahStock;

    @Column(name = "tahun_penerbit")
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
