package com.online.perpustakaan.repository;

import com.online.perpustakaan.dto.master.DtoTransaksiView;
import com.online.perpustakaan.model.MstTransaksi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<MstTransaksi, Long> {

    @Query(value = "select distinct a.* from mst_transaksi a where a.transaksi_id = :transaksi_id",nativeQuery = true)
    MstTransaksi findByTransaksi(@Param("transaksi_id") Long transaksiId);

    @Query(value = "select distinct a.* from mst_transaksi a where a.no_tiket = :no_tiket",nativeQuery = true)
    MstTransaksi findByTiket(@Param("no_tiket") String noTiket);

    @Query(value = "SELECT " +
            "    a.transaksi_id AS transaksiId, " +
            "    CASE " +
            "        WHEN a.tgl_pinjam < a.tgl_pengembalian - INTERVAL '1 DAY' THEN 'AKTIF' " +
            "        ELSE 'TIDAK AKTIF' " +
            "    END AS status, " +
            "    a.no_tiket AS noTiket, " +
            "    a.buku_id AS bukuId, " +
            "    b.kode_buku AS kodeBuku, " +
            "    a.user_id AS userId, " +
            "    a.keterangan, " +
            "    a.jumlah, " +
            "    a.tgl_pinjam, " +
            "    a.tgl_pengembalian " +
            "FROM " +
            "    mst_transaksi a " +
            "    INNER JOIN mst_buku b ON a.buku_id = b.buku_id " +
            "ORDER BY " +
            "    a.transaksi_id, " +
            "    status ASC", nativeQuery = true)
    List<Object[]> findAllWithCustomMapping();

    @Query(value = "SELECT " +
            "    a.transaksi_id AS transaksiId, " +
            "    CASE " +
            "        WHEN a.tgl_pinjam < a.tgl_pengembalian - INTERVAL '1 DAY' THEN 'AKTIF' " +
            "        ELSE 'TIDAK AKTIF' " +
            "    END AS status, " +
            "    a.no_tiket AS noTiket, " +
            "    a.buku_id AS bukuId, " +
            "    b.kode_buku AS kodeBuku, " +
            "    a.user_id AS userId, " +
            "    a.keterangan, " +
            "    a.jumlah, " +
            "    a.tgl_pinjam, " +
            "    a.tgl_pengembalian " +
            "FROM " +
            "    mst_transaksi a " +
            "    INNER JOIN mst_buku b ON a.buku_id = b.buku_id " +
            "WHERE " +
            "    CASE " +
            "            WHEN a.tgl_pinjam < a.tgl_pengembalian - INTERVAL '1 DAY' THEN 'AKTIF' " +
            "            ELSE 'TIDAK AKTIF' " +
            "        END = :status " +
            "    AND a.no_tiket LIKE %:noTiket% " +
            "    AND b.kode_buku LIKE %:kodeBuku% " +
            "ORDER BY " +
            "    a.transaksi_id, " +
            "    status ASC", nativeQuery = true)
    List<Object[]> findByLikeCriteria(@Param("status") String status,
                                      @Param("noTiket") String noTiket,
                                      @Param("kodeBuku") String kodeBuku);
}
