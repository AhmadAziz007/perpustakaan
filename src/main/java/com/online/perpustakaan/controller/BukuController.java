package com.online.perpustakaan.controller;

import com.online.perpustakaan.dto.master.MstBukuDTO;
import com.online.perpustakaan.service.BukuService;
import com.online.perpustakaan.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/buku")
public class BukuController {
    @Autowired
    BukuService bukuService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/save")
    public ResponseEntity<?> createbuku(@RequestHeader Map<String,String> header, @RequestBody MstBukuDTO request){
        jwtService.filter(header);
        return bukuService.createBuku(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updatebuku(@RequestHeader Map<String,String> header, @RequestBody MstBukuDTO request){
        jwtService.filter(header);
        return bukuService.updateBuku(request);
    }

    @GetMapping("/getkode")
    public ResponseEntity<?> getbuku(@RequestHeader Map<String,String> header, @RequestBody MstBukuDTO request){
        jwtService.filter(header);
        String kodeBuku = request.getKodeBuku();

        return bukuService.getBuku(kodeBuku);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getall(@RequestHeader Map<String,String> header){
        jwtService.filter(header);
        return bukuService.getAll();
    }

    @DeleteMapping("/delete/{buku}")
    public ResponseEntity<?> deletebuku(@RequestHeader Map<String,String> header, @PathVariable String buku){
        jwtService.filter(header);
        return bukuService.deleteBuku(buku);
    }
}
