package com.example.auth.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.dto.LoginRequest;
import com.example.auth.model.UserEntity;
import com.example.auth.repository.UserRepository;
import com.example.auth.security.JwtTokenProvider;
import com.example.auth.service.LoginAttemptService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final LoginAttemptService loginAttemptService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();

        if (loginAttemptService.isBlocked(username)) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS)
                    .body("Çok fazla hatalı giriş yapıldı. Lütfen 10 dakika sonra tekrar deneyin.");
        }

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            loginAttemptService.loginSucceeded(username); // Başarılı girişte sıfırla
            String token = jwtTokenProvider.generateToken(authentication.getName());
            return ResponseEntity.ok().body("Bearer " + token);

        } catch (AuthenticationException ex) {
            loginAttemptService.loginFailed(username); // Başarısız girişte arttır
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Kullanıcı adı veya şifre hatalı.");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody LoginRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Bu kullanıcı adı zaten alınmış.");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER");

        userRepository.save(user);
        return ResponseEntity.ok("Kullanıcı başarıyla kaydedildi.");
    }
}
