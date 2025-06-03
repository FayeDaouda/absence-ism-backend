package com.ism.absences.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ism.absences.entity.Utilisateur;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "maCleSecreteTrèsLongueEtComplexe";

    public String generateToken(Utilisateur utilisateur) {
        return Jwts.builder()
            .setSubject(utilisateur.getEmail())
            .claim("role", utilisateur.getRole().name())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    // Méthodes pour valider et lire token (à ajouter plus tard)
}
