package com.ism.absences.controller;

import com.ism.absences.dto.request.LoginRequest;
import com.ism.absences.dto.response.LoginResponse;
import com.ism.absences.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
    try {
        LoginResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    } catch (RuntimeException e) {
        return ResponseEntity.status(401).body(Map.of("error", e.getMessage()));
    }
}

}
