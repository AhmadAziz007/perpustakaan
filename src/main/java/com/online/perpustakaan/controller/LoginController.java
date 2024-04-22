package com.online.perpustakaan.controller;

import com.online.perpustakaan.dto.master.MstLoginDTO;
import com.online.perpustakaan.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> savelogin(@RequestBody MstLoginDTO request){
        return loginService.login(request);
    }


}
