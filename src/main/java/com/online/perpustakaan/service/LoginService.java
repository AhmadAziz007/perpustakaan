package com.online.perpustakaan.service;

import com.online.perpustakaan.dto.master.MstLoginDTO;
import org.springframework.http.ResponseEntity;

public interface LoginService {

    public ResponseEntity<?> login(MstLoginDTO requestDTO);
}
