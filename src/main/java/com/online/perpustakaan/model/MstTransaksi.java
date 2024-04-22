package com.online.perpustakaan.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "mst_transaksi", schema = "public")
public class MstTransaksi {
    @Id
    @SequenceGenerator(name = "mst_transaksi_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_transaksi_seq")
    @Column(name = "transaksi_id")
    private Long transaksiId;

    @Column(name = "no_tiket")
    private String noTiket;

    @Column(name = "status")
    private String status;

    @Column(name = "buku_id")
    private Long bukuId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "keterangan", length = 20)
    private String keterangan;

    @Column(name = "jumlah")
    private Integer jumlah;

    @Column(name = "tgl_pinjam")
    private Date tglPinjam;

    @Column(name = "tgl_pengembalian")
    private Date tglPengembalian;

    public Long getBukuId() {
        return bukuId;
    }

    public String getNoTiket() {
        return noTiket;
    }

    public void setNoTiket(String noTiket) {
        this.noTiket = noTiket;
    }

    public void setBukuId(Long bukuId) {
        this.bukuId = bukuId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTransaksiId() {
        return transaksiId;
    }

    public void setTransaksiId(Long transaksiId) {
        this.transaksiId = transaksiId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Integer getJumlah() {
        return jumlah;
    }

    public void setJumlah(Integer jumlah) {
        this.jumlah = jumlah;
    }

    public Date getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(Date tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    public Date getTglPengembalian() {
        return tglPengembalian;
    }

    public void setTglPengembalian(Date tglPengembalian) {
        this.tglPengembalian = tglPengembalian;
    }
}
