package com.online.perpustakaan.service.implement;

import com.online.perpustakaan.common.ResponseDTO;
import com.online.perpustakaan.dto.master.MstBukuDTO;
import com.online.perpustakaan.model.MstBuku;
import com.online.perpustakaan.repository.BukuRepository;
import com.online.perpustakaan.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "bukuService")
public class BukuServiceImplement implements BukuService {

    @Autowired
    BukuRepository bukuRepo;

    @Override
    public ResponseEntity<?> createBuku(MstBukuDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        try{
            MstBuku bukuEntity = new MstBuku();
            MstBuku mstBuku = bukuRepo.findByBuku(requestDTO.getKodeBuku());
            if (mstBuku != null) {
                response.setCode("409");
                response.setMessage("buku " + requestDTO.getKodeBuku() + " already exists");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
            }
            if (requestDTO.getKodeBuku() == null ||
                    requestDTO.getJudul() == null ||
                    requestDTO.getPengarang() == null ||
                    requestDTO.getJumlahStock() == null ||
                    requestDTO.getTahunPenerbit() == null) {
                response.setCode("204");
                response.setMessage("Field mandatory tidak boleh kosong");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            bukuEntity.setKodeBuku(requestDTO.getKodeBuku());
            bukuEntity.setJudul(requestDTO.getJudul());
            bukuEntity.setPengarang(requestDTO.getPengarang());
            bukuEntity.setJumlahStock(requestDTO.getJumlahStock());
            bukuEntity.setTahunPenerbit(requestDTO.getTahunPenerbit());

            bukuRepo.save(bukuEntity);

            response.setCode("200");
            response.setData(bukuEntity);
            response.setMessage("Master Buku has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e){
            response.setCode("500");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> updateBuku(MstBukuDTO requestDTO) {
        ResponseDTO response = new ResponseDTO();
        try {
            MstBuku mstBuku = bukuRepo.findByBuku(requestDTO.getKodeBuku());
            if (mstBuku == null) {
                response.setCode("204");
                response.setMessage("Buku id not found");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            if (requestDTO.getKodeBuku() == null ||
                    requestDTO.getJudul() == null ||
                    requestDTO.getPengarang() == null ||
                    requestDTO.getJumlahStock() == null ||
                    requestDTO.getTahunPenerbit() == null) {
                response.setCode("204");
                response.setMessage("Field mandatory tidak boleh kosong");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            mstBuku.setKodeBuku(requestDTO.getKodeBuku());
            mstBuku.setJudul(requestDTO.getJudul());
            mstBuku.setPengarang(requestDTO.getPengarang());
            mstBuku.setJumlahStock(requestDTO.getJumlahStock());
            mstBuku.setTahunPenerbit(requestDTO.getTahunPenerbit());

            bukuRepo.save(mstBuku);

            response.setCode("200");
            response.setData(mstBuku);
            response.setMessage("Master Buku has been saved successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getBuku(String kodeBuku) {
        kodeBuku = kodeBuku.toUpperCase();

        ResponseDTO response = new ResponseDTO();
        try{
            MstBuku mstBuku = bukuRepo.findByBuku(kodeBuku);
            if (mstBuku == null) {
                response.setCode("204");
                response.setMessage("Kode Buku " + kodeBuku + " not found");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }
            response.setCode("200");
            response.setData(mstBuku);
            response.setMessage("Get Data By Master Buku successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<?> getAll() {
        ResponseDTO response = new ResponseDTO();
        List<MstBuku> mstBuku = bukuRepo.findAll();

        response.setCode("200");
        response.setData(mstBuku);
        response.setMessage("Get Data By Master Buku successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteBuku(String buku) {
        ResponseDTO response = new ResponseDTO();
        try{
            MstBuku mstBuku = bukuRepo.findByBuku(buku);
            if (mstBuku == null) {
                response.setCode("204");
                response.setMessage("Kode Buku " + buku + " not found");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
            }

            bukuRepo.delete(mstBuku);
            response.setCode("200");
            response.setData(mstBuku);
            response.setMessage("delete Data By Master Buku successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            response.setCode("500");
            response.setMessage("Internal Server Error: " + e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
