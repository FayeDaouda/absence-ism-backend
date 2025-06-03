package com.ism.absences.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.ism.absences.dto.request.LoginRequest;
import com.ism.absences.dto.response.LoginResponse;
import com.ism.absences.entity.Utilisateur;
import com.ism.absences.repository.UtilisateurRepository;
import com.ism.absences.security.JwtUtil;

@Service
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Utilisateur utilisateur = utilisateurRepository.findByEmail(loginRequest.getEmail())
            .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    
        if (!passwordEncoder.matches(loginRequest.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new RuntimeException("Mot de passe incorrect");
        }
    
        String token = jwtUtil.generateToken(utilisateur);
    
        // Nouveau constructeur avec id
        return new LoginResponse(utilisateur.getId(), utilisateur.getEmail(), utilisateur.getRole(), token);


    }
    
    
}
