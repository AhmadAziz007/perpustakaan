package com.online.perpustakaan.service.implement;

import com.online.perpustakaan.common.ResponseDTO;
import com.online.perpustakaan.dto.master.DtoTransaksiView;
import com.online.perpustakaan.dto.master.MstTransaksiDTO;
import com.online.perpustakaan.model.MstBuku;
import com.online.perpustakaan.model.MstTransaksi;
import com.online.perpustakaan.repository.BukuRepository;
import com.online.perpustakaan.repository.TransaksiRepository;
import com.online.perpustakaan.repository.UserRepository;
import com.online.perpustakaan.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service(value = "transaksiService")
public class TransaksiServiceImplement implements TransaksiService {

    @Autowired
    TransaksiRepository transaksiRepo;

    @Autowired
    BukuRepository bukuRepo;

    @Autowired
    UserRepository userRepo;


    @Override
    public ResponseEntity<?> createTransaksi(MstTransaksiDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        try{

            MstTransaksi transaksi = new MstTransaksi();
            MstTransaksi mstTransaksi = transaksiRepo.findByTiket(requestDTO.getNoTiket());
            if (mstTransaksi != null) {
                response.setCode("409");
                response.setMessage("user hanya bisa meminjam 1 buku dan apabila sedang meminjam buku harus dikembalikan terlebih dahulu agar dapat meminjam Kembali");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
            MstBuku buku = bukuRepo.findByBukuId(requestDTO.getBukuId());
            if (buku == null) {
                response.setCode("204");
                response.setMessage("Buku id not found");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (requestDTO.getBukuId() == null &&
                    requestDTO.getUserId() == null &&
                    requestDTO.getNoTiket() == null &&
                    requestDTO.getJumlah() == null &&
                    requestDTO.getKeterangan() == null &&
                    requestDTO.getTglPinjam() == null &&
                    requestDTO.getTglPengembalian() == null) {
                response.setCode("204");
                response.setMessage("Field mandatory tidak boleh kosong");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            transaksi.setBukuId(requestDTO.getBukuId());
            transaksi.setUserId(requestDTO.getUserId());
            transaksi.setNoTiket(requestDTO.getNoTiket());
            transaksi.setKeterangan(requestDTO.getKeterangan());
            transaksi.setJumlah(requestDTO.getJumlah());
            transaksi.setTglPinjam(requestDTO.getTglPinjam());
            transaksi.setTglPengembalian(requestDTO.getTglPengembalian());

            transaksiRepo.save(transaksi);
            response.setCode("200");
            response.setData(transaksi);
            response.setMessage("Master Transaksi has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateTransaksi(MstTransaksiDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        try{
            MstTransaksi mstTransaksi = transaksiRepo.findByTiket(requestDTO.getNoTiket());
            MstBuku buku = bukuRepo.findByBukuId(requestDTO.getBukuId());
            if (buku == null) {
                response.setCode("204");
                response.setMessage("Buku id not found");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (requestDTO.getBukuId() == null ||
                    requestDTO.getUserId() == null ||
                    requestDTO.getNoTiket() == null ||
                    requestDTO.getJumlah() == null ||
                    requestDTO.getKeterangan() == null ||
                    requestDTO.getTglPinjam() == null ||
                    requestDTO.getTglPengembalian() == null) {
                response.setCode("204");
                response.setMessage("Field mandatory tidak boleh kosong");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            mstTransaksi.setTransaksiId(requestDTO.getTransaksiId());
            mstTransaksi.setBukuId(requestDTO.getBukuId());
            mstTransaksi.setUserId(requestDTO.getUserId());
            mstTransaksi.setNoTiket(requestDTO.getNoTiket());
            mstTransaksi.setKeterangan(requestDTO.getKeterangan());
            mstTransaksi.setJumlah(requestDTO.getJumlah());
            mstTransaksi.setTglPinjam(requestDTO.getTglPinjam());
            mstTransaksi.setTglPengembalian(requestDTO.getTglPengembalian());

            transaksiRepo.save(mstTransaksi);
            response.setCode("200");
            response.setData(mstTransaksi);
            response.setMessage("Master Transaksi has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<DtoTransaksiView>> getTransaksi(String noTiket, String status, String kodeBuku) {
        noTiket = noTiket.toUpperCase();
        status = status.toUpperCase();
        kodeBuku = kodeBuku.toUpperCase();

        List<Object[]> resultList = transaksiRepo.findByLikeCriteria(noTiket, status, kodeBuku);
        List<DtoTransaksiView> transaksiList = new ArrayList<>();

        for (Object[] obj : resultList) {
            DtoTransaksiView transaksiView = new DtoTransaksiView();
            transaksiView.setTransaksiId((Long) obj[0]);
            transaksiView.setStatus((String) obj[1]);
            transaksiView.setNoTiket((String) obj[2]);
            transaksiView.setBukuId((Long) obj[3]);
            transaksiView.setKodeBuku((String) obj[4]);
            transaksiView.setUserId((Long) obj[5]);
            transaksiView.setUserName((String) obj[6]);
            transaksiView.setKeterangan((String) obj[7]);
            transaksiView.setJumlah((Integer) obj[8]);
            transaksiView.setTglPinjam((Date) obj[9]);
            transaksiView.setTglPengembalian((Date) obj[10]);
            transaksiList.add(transaksiView);
        }

        return ResponseEntity.ok(transaksiList);
    }

    @Override
    public ResponseEntity<List<DtoTransaksiView>> getAll() {
        List<Object[]> resultList = transaksiRepo.findAllWithCustomMapping();
        List<DtoTransaksiView> transaksiList = new ArrayList<>();

        for (Object[] obj : resultList) {
            DtoTransaksiView transaksiView = new DtoTransaksiView();
            transaksiView.setTransaksiId((Long) obj[0]);
            transaksiView.setStatus((String) obj[1]);
            transaksiView.setNoTiket((String) obj[2]);
            transaksiView.setBukuId((Long) obj[3]);
            transaksiView.setKodeBuku((String) obj[4]);
            transaksiView.setUserId((Long) obj[5]);
            transaksiView.setUserName((String) obj[6]);
            transaksiView.setKeterangan((String) obj[7]);
            transaksiView.setJumlah((Integer) obj[8]);
            transaksiView.setTglPinjam((Date) obj[9]);
            transaksiView.setTglPengembalian((Date) obj[10]);
            transaksiList.add(transaksiView);
        }

        return ResponseEntity.ok(transaksiList);
    }
}
