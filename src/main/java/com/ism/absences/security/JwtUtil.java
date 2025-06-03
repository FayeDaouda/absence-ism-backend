package com.ism.absences.security;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.ism.absences.entity.Utilisateur;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    // Clé secrète 100% ASCII, sans accents ni caractères spéciaux
    private final String SECRET_KEY = "maCleSecreteTresLongueEtComplexe123456";

    public String generateToken(Utilisateur utilisateur) {
        return Jwts.builder()
            .setSubject(utilisateur.getEmail())
            .claim("role", utilisateur.getRole().name())
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10h de validité
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // clé en bytes ASCII
            .compact();
    }

    // Ajoute ici les méthodes pour valider et parser le token JWT si besoin

}
