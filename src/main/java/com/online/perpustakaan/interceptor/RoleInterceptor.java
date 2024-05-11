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

import java.util.Map;

import static io.vertx.core.http.impl.HttpClientConnection.log;

@Component
public class RoleInterceptor implements HandlerInterceptor {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    public boolean preHandle(Map<String,String> header, HttpServletRequest request , HttpServletResponse response, Object handler) throws Exception {
        String bearer = header.get("authorization");
        String split[] = bearer.split(" ");
        bearer = split[1];
        log.info(bearer);

        MstUser user = userRepository.findByToken(bearer);
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
        if ((roleName.equalsIgnoreCase("Admin 1") || roleName.equalsIgnoreCase("Admin 2")) && (
                method.equalsIgnoreCase("POST") && requestURI.contains("/user/register") ||
                        method.equalsIgnoreCase("PUT") && requestURI.contains("/user/update") ||
                        method.equalsIgnoreCase("DELETE") && requestURI.contains("/user/delete"))) {
            return true;
        } else if ((roleName.equalsIgnoreCase("Admin 1") || roleName.equalsIgnoreCase("Admin 2") || roleName.equalsIgnoreCase("Customer")) && (
                method.equalsIgnoreCase("GET") && requestURI.contains("/user/getuser") ||
                        method.equalsIgnoreCase("GET") && requestURI.contains("/user/getall"))) {
            return true;
        }

        throw new ForbiddenAccessException("Access forbidden for this role or endpoint");
    }

    private String getBearerToken(Map<String,String> bearer){
        return bearer.get("Authorization");
    }
}
