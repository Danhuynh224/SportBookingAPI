package com.example.apiproject.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    // Thay thế khóa bí mật bằng SecretKey với độ dài đủ cho HS256
    private final SecretKey SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generate JWT token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .signWith(SECRET_KEY)  // Sử dụng SECRET_KEY đã tạo
                .compact();
    }

    // Extract username from JWT token
    public String extractUsername(String token) {
        Claims claims = Jwts.parser()  // Sử dụng parserBuilder() thay cho parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)  // Parse JWT token để lấy claims
                .getBody();  // Lấy body của claims

        return claims.getSubject();  // Trả về subject (username)
    }


    public boolean validateToken(String token) {
        try {
            Jwts.parser() // Sử dụng parserBuilder() thay cho parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);  // Parse JWT token
            return true;  // Token hợp lệ
        } catch (Exception e) {
            return false;  // Token không hợp lệ
        }
    }
}
