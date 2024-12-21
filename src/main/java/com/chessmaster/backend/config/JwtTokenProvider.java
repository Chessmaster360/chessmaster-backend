package com.chessmaster.backend.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "mySecretKey";

    // Generar un token JWT
    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact();
    }

    // Obtener el nombre de usuario del token JWT
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
            .setSigningKey(SECRET_KEY)
            .parseClaimsJws(token)
            .getBody();

        return claims.getSubject();
    }

    // Validar el token JWT
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true; // Token válido
        } catch (ExpiredJwtException e) {
            System.out.println("Token expirado: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Token mal formado: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Token no soportado: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Token vacío: " + e.getMessage());
        }
        return false; // Token inválido
    }
}
