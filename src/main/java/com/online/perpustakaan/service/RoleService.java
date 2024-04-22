package com.online.perpustakaan.service;

import com.online.perpustakaan.dto.master.MstRoleDTO;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    public ResponseEntity<?> createRole(MstRoleDTO requestDTO);

    public ResponseEntity<?> updateRole(MstRoleDTO requestDTO);

    public ResponseEntity<?> getRole(String name);

    public ResponseEntity<?> getAll();

    public ResponseEntity<?> deleteRole(String name);
}
