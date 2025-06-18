package com.example.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.security.JwtTokenProvider;
import com.example.auth.service.TokenBlacklistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class LogoutController {

    private final TokenBlacklistService tokenBlacklistService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") String header) {
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            long expiration = jwtTokenProvider.getExpirationFromToken(token);
            tokenBlacklistService.blacklistToken(token, expiration);

            return ResponseEntity.ok("Logout başarılı, token geçersizleştirildi.");
        }
        return ResponseEntity.badRequest().body("Geçersiz token.");
    }
}
