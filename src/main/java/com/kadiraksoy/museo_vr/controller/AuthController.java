package com.kadiraksoy.museo_vr.controller;

import com.kadiraksoy.museo_vr.dto.request.LoginRequest;
import com.kadiraksoy.museo_vr.dto.request.RegisterRequest;
import com.kadiraksoy.museo_vr.dto.response.AuthenticationResponse;
import com.kadiraksoy.museo_vr.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // Kullanıcı giriş işlemi
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

    // Kullanıcı kayıt işlemi
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        String message = authService.register(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(message);
    }

    // OTP doğrulama işlemi
    @PostMapping("/verify-account")
    public ResponseEntity<String> verifyOtp(
            @RequestParam("email") String email,
            @RequestParam("otp") String otp) {
        String message = authService.verifyOtp(email, otp);
        return ResponseEntity.ok(message);
    }

    // OTP yenileme işlemi
    @PostMapping("/regenerate-otp")
    public ResponseEntity<String> regenerateOtp(@RequestParam("email") String email) {
        String message = authService.regenerateOtp(email);
        return ResponseEntity.ok(message);
    }
}
