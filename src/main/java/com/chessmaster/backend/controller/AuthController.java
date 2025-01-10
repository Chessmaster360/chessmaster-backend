package com.chessmaster.backend.controller;

import com.chessmaster.backend.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthController {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Map<String, String> users; // Simulación de base de datos de usuarios

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        if (users.containsKey(username) && passwordEncoder.matches(password, users.get(username))) {
            // Generar token JWT
            return jwtTokenProvider.generateToken(username);
        }

        throw new RuntimeException("Credenciales inválidas");
    }

    public Map<String, String> getUsers() {
        return users;
    }
}