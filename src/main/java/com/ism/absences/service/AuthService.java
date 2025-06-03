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
    private final PasswordEncoder passwordEncoder;  // Ajouté
    private final JwtUtil jwtUtil;

    public AuthService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByEmail(loginRequest.getEmail());
        if (utilisateurOpt.isEmpty()) {
            throw new RuntimeException("Utilisateur non trouvé");
        }
    
        Utilisateur utilisateur = utilisateurOpt.get();
    
        if (!passwordEncoder.matches(loginRequest.getMotDePasse(), utilisateur.getMotDePasse())) {
            throw new RuntimeException("Mot de passe incorrect");
        }
    
        // On crée la réponse sans token
        LoginResponse response = new LoginResponse(utilisateur.getEmail(), utilisateur.getRole());
    
        return response;
    }
    
}
