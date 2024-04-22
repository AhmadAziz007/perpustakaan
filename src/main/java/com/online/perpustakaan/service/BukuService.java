package com.online.perpustakaan.service;

import com.online.perpustakaan.dto.master.MstBukuDTO;
import org.springframework.http.ResponseEntity;

public interface BukuService {

    public ResponseEntity<?> createBuku(MstBukuDTO requestDTO);

    public ResponseEntity<?> updateBuku(MstBukuDTO requestDTO);

    public ResponseEntity<?> getBuku(String buku);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> deleteBuku(String buku);

}
