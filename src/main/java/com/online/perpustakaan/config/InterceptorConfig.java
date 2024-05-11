package com.online.perpustakaan.config;

import com.online.perpustakaan.interceptor.RoleInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Autowired
    private RoleInterceptor roleInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(roleInterceptor)
                .addPathPatterns("/user/**") // Aturan akses untuk MstUser
                .addPathPatterns("/buku/**")
                .addPathPatterns("/role/**")
                .addPathPatterns("/transaksi/**"); // Aturan akses untuk MstBuku
    }
}
