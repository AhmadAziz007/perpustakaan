package com.online.perpustakaan.controller;

import com.online.perpustakaan.dto.master.DtoTransaksiView;
import com.online.perpustakaan.dto.master.MstTransaksiDTO;
import com.online.perpustakaan.service.JwtService;
import com.online.perpustakaan.service.TransaksiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/transaksi")
public class TransaksiController {

    @Autowired
    TransaksiService transaksiService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> createTransaksi(@RequestHeader Map<String,String> header, @RequestBody MstTransaksiDTO request){
        jwtService.filter(header);
        return transaksiService.createTransaksi(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTransaksi(@RequestHeader Map<String,String> header, @RequestBody MstTransaksiDTO request){
        jwtService.filter(header);
        return transaksiService.updateTransaksi(request);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAllTransaksi(@RequestHeader Map<String,String> header){
        jwtService.filter(header);
        return transaksiService.getAll();
    }

//    @GetMapping("/get-by-like-criteria")
//    public ResponseEntity<?> getTransaksi(
//            @RequestParam("noTiket") String noTiket,
//            @RequestParam("status") String status,
//            @RequestParam("kodeBuku") String kodeBuku) {
//        return transaksiService.getTransaksi(noTiket, status, kodeBuku);
//    }

    @GetMapping("/search")
    public ResponseEntity<List<DtoTransaksiView>> getTransaksi(@RequestHeader Map<String,String> header, @RequestBody DtoTransaksiView searchCriteria) {
        jwtService.filter(header);
        String status = searchCriteria.getStatus();
        String noTiket = searchCriteria.getNoTiket();
        String kodeBuku = searchCriteria.getKodeBuku();

        return transaksiService.getTransaksi(status, noTiket,  kodeBuku);
    }

}
