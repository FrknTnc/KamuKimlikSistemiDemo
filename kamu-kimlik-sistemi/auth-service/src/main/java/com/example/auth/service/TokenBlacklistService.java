package com.example.auth.service;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class TokenBlacklistService {

    private final RedisTemplate<String, Object> redisTemplate;

    public TokenBlacklistService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void blacklistToken(String token, long expirationSeconds) {
        redisTemplate.opsForValue().set("BLACKLIST:" + token, true, Duration.ofSeconds(expirationSeconds));
    }

    public boolean isTokenBlacklisted(String token) {
        Boolean blacklisted = (Boolean) redisTemplate.opsForValue().get("BLACKLIST:" + token);
        return blacklisted != null && blacklisted;
    }
}
