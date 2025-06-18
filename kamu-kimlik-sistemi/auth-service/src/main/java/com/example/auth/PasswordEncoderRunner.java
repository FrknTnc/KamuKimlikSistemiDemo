package com.example.auth;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordEncoderRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        String rawPassword = "1234";
        String encodedPassword = new BCryptPasswordEncoder().encode(rawPassword);
        System.out.println("Encoded password for '" + rawPassword + "': " + encodedPassword);
    }
}
