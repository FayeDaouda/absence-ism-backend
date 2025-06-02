package com.ism.absences.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ism.absences.dto.request.LoginRequest;
import com.ism.absences.dto.response.LoginResponse;
import com.ism.absences.entity.Utilisateur;
import com.ism.absences.repository.UtilisateurRepository;
import com.ism.absences.security.JwtUtil;

@Service
public class AuthService {

    private final UtilisateurRepository utilisateurRepository;
    private final JwtUtil jwtUtil;

    public AuthService(UtilisateurRepository utilisateurRepository, JwtUtil jwtUtil) {
        this.utilisateurRepository = utilisateurRepository;
        this.jwtUtil = jwtUtil;
    }

    public LoginResponse login(LoginRequest loginRequest) {
        Optional<Utilisateur> userOpt = utilisateurRepository.findByEmail(loginRequest.getEmail());

        if (userOpt.isEmpty() || 
            !userOpt.get().getMotDePasse().equals(loginRequest.getMotDePasse())) {
            throw new RuntimeException("Identifiants invalides");
        }

        Utilisateur utilisateur = userOpt.get();
        String token = jwtUtil.generateToken(utilisateur.getEmail(), utilisateur.getRole().name());


        return new LoginResponse(token, utilisateur.getRole(), utilisateur.getEmail());
    }
}
