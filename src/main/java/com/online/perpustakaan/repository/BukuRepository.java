package com.online.perpustakaan.repository;

import com.online.perpustakaan.model.MstBuku;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BukuRepository extends JpaRepository<MstBuku, Long> {
    @Query(value = "select distinct a.* from mst_buku a where a.item_id = :itemId",nativeQuery = true)
    MstBuku findByItemId(@Param("itemId") Long itemId);

    @Query(value = "select distinct a.* from mst_buku a where a.kode_buku = :kode_buku",nativeQuery = true)
    MstBuku findByBuku(@Param("kode_buku") String kodeBuku);

    @Query(value = "select distinct a.* from mst_buku a where a.buku_id = :buku_id",nativeQuery = true)
    MstBuku findByBukuId(@Param("buku_id") Long bukuId);

    @Query(value = "select distinct a.* from mst_buku a order by a.kode_buku asc ",nativeQuery = true)
    List<MstBuku> findAll();
}
