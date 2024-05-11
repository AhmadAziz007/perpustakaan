package com.online.perpustakaan.interceptor;

import com.online.perpustakaan.exception.ForbiddenAccessException;
import com.online.perpustakaan.exception.JwtException;
import com.online.perpustakaan.model.MstRole;
import com.online.perpustakaan.model.MstUser;
import com.online.perpustakaan.repository.RoleRepository;
import com.online.perpustakaan.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

@Component
public class RoleInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Map<String, String> header = getAuthorizationHeader(request);
        String bearerToken = getBearerToken(header);
        if (bearerToken == null) {
            throw new JwtException();
        }

        MstUser user = userRepository.findByToken(bearerToken);
        if (user == null) {
            throw new JwtException();
        }

        Long roleId = user.getRoleId();
        MstRole role = roleRepository.findById(roleId).orElse(null);
        if (role == null) {
            throw new JwtException();
        }

        String roleName = role.getRoleName();
        String method = request.getMethod();
        String requestURI = request.getRequestURI();

        // Check access based on role_name and request URI
        if ((roleName.equalsIgnoreCase("Admin 1") || roleName.equalsIgnoreCase("Admin 2")
                || roleName.equalsIgnoreCase("Customer")) &&
                        method.equalsIgnoreCase("PUT") && requestURI.contains("/user/update")  ||

                        method.equalsIgnoreCase("GET") && requestURI.contains("/buku/getkode") ||
                        method.equalsIgnoreCase("GET") && requestURI.contains("/buku/getall") ||

                        method.equalsIgnoreCase("POST") && requestURI.contains("/transaksi/save") ||
                        method.equalsIgnoreCase("GET") && requestURI.contains("/transaksi/get-all") ||
                        method.equalsIgnoreCase("GET") && requestURI.contains("/transaksi/search")) {
            return true;
        } else if ((roleName.equalsIgnoreCase("Admin 1") || roleName.equalsIgnoreCase("Admin 2")) && (
                        method.equalsIgnoreCase("GET") && requestURI.contains("/user/getuser") ||
                        method.equalsIgnoreCase("GET") && requestURI.contains("/user/getall") ||
                        method.equalsIgnoreCase("DELETE") && requestURI.contains("/user/delete") ||

                        method.equalsIgnoreCase("POST") && requestURI.contains("/role/save") ||
                        method.equalsIgnoreCase("PUT") && requestURI.contains("/role/update") ||
                        method.equalsIgnoreCase("GET") && requestURI.contains("/role/getrole") ||
                        method.equalsIgnoreCase("GET") && requestURI.contains("/role/getall") ||
                        method.equalsIgnoreCase("DELETE") && requestURI.contains("/role/delete") ||

                        method.equalsIgnoreCase("POST") && requestURI.contains("/buku/save") ||
                        method.equalsIgnoreCase("PUT") && requestURI.contains("/buku/update") ||
                        method.equalsIgnoreCase("GET") && requestURI.contains("/buku/getkode") ||
                        method.equalsIgnoreCase("GET") && requestURI.contains("/buku/getall") ||
                        method.equalsIgnoreCase("DELETE") && requestURI.contains("/buku/delete") ||

                        method.equalsIgnoreCase("POST") && requestURI.contains("/transaksi/save") ||
                        method.equalsIgnoreCase("PUT") && requestURI.contains("/transaksi/update") ||
                        method.equalsIgnoreCase("GET") && requestURI.contains("/transaksi/get-all") ||
                        method.equalsIgnoreCase("GET") && requestURI.contains("/transaksi/search"))) {
            return true;
        }

        throw new ForbiddenAccessException("Access forbidden for this role or endpoint");
    }

    private Map<String, String> getAuthorizationHeader(HttpServletRequest request) {
        // Ambil header Authorization dari request
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7); // Potong "Bearer " dari token
            Map<String, String> header = new HashMap<>();
            header.put("authorization", token);
            return header;
        }
        return null;
    }

    private String getBearerToken(Map<String, String> header) {
        if (header != null) {
            return header.get("authorization");
        }
        return null;
    }
}
