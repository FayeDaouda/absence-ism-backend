package com.ism.absences.controller;

import com.ism.absences.dto.LoginResponse;
import com.ism.absences.entity.User;
import com.ism.absences.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AuthController {

    private final UserRepository userRepository;

    @PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> loginRequest) {
    String email = loginRequest.get("email");
    String password = loginRequest.get("password");

    if (email == null || password == null) {
        return ResponseEntity.badRequest().body("Email et mot de passe requis");
    }

    Optional<User> userOpt = userRepository.findByEmail(email);
    if (userOpt.isEmpty()) {
        return ResponseEntity.status(401).body("Email non trouvé");
    }

    User user = userOpt.get();
    if (!user.getPassword().equals(password)) {
        return ResponseEntity.status(401).body("Mot de passe incorrect");
    }

    LoginResponse response = new LoginResponse(
        user.getId(),
        user.getEmail(),
        user.getRole().name(),
        user.getMatricule() // Assure-toi que ce champ existe dans ton entité User
    );

    return ResponseEntity.ok(response);
}

}
