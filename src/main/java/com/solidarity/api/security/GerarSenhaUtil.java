package com.solidarity.api.security;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GerarSenhaUtil {

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("teste12345678"));
    }

}
