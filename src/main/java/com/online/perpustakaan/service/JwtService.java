package com.online.perpustakaan.service;

import com.online.perpustakaan.dto.master.UserJwtDTO;

import java.util.Map;

public interface JwtService {
    public UserJwtDTO filter(Map<String,String> header);
}
