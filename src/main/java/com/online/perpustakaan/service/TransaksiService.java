package com.online.perpustakaan.service;

import com.online.perpustakaan.common.ResponseDTO;
import com.online.perpustakaan.dto.master.DtoTransaksiView;
import com.online.perpustakaan.dto.master.MstTransaksiDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TransaksiService {

    public ResponseEntity<?> createTransaksi(MstTransaksiDTO requestDTO);

    public ResponseEntity<?> updateTransaksi(MstTransaksiDTO requestDTO);

    public ResponseEntity<List<DtoTransaksiView>> getTransaksi(String noTiket, String status, String kodeBuku);

    public ResponseEntity<List<DtoTransaksiView>> getAll();

}
