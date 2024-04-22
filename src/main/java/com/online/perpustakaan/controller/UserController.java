package com.online.perpustakaan.controller;

import com.online.perpustakaan.dto.master.MstUserDTO;
import com.online.perpustakaan.service.JwtService;
import com.online.perpustakaan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<?> createRole(@RequestHeader Map<String,String> header, @RequestBody MstUserDTO request){
        jwtService.filter(header);
        return userService.createUser(request);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateRole(@RequestHeader Map<String,String> header, @RequestBody MstUserDTO request){
        jwtService.filter(header);
        return userService.updateUser(request);
    }

    @GetMapping("/getuser/{email}")
    public ResponseEntity<?> getRole(@RequestHeader Map<String,String> header, @PathVariable String email){
        jwtService.filter(header);
        return userService.getUser(email);
    }

    @GetMapping("/getall")
    public ResponseEntity<?> getall(@RequestHeader Map<String,String> header){
        jwtService.filter(header);
        return userService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteRole(@RequestHeader Map<String,String> header, @PathVariable Long id){
        jwtService.filter(header);
        return userService.deleteUser(id);
    }
}
