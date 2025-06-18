package com.example.auth.service;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class LoginAttemptService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final int MAX_ATTEMPT = 5;

    public LoginAttemptService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void loginFailed(String username) {
        String key = "LOGIN_ATTEMPT:" + username;
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        Integer attempts = (Integer) ops.get(key);
        if (attempts == null) attempts = 0;
        attempts++;
        ops.set(key, attempts, Duration.ofMinutes(10)); // 10 dk sonra sıfırlanır
    }

    public boolean isBlocked(String username) {
        String key = "LOGIN_ATTEMPT:" + username;
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        Integer attempts = (Integer) ops.get(key);
        return attempts != null && attempts >= MAX_ATTEMPT;
    }

    public void loginSucceeded(String username) {
        redisTemplate.delete("LOGIN_ATTEMPT:" + username);
    }
}
