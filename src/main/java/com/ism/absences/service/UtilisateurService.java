package com.ism.absences.service;

import com.ism.absences.entity.Utilisateur;
import com.ism.absences.repository.UtilisateurRepository;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final PasswordEncoder passwordEncoder;

    public UtilisateurService(UtilisateurRepository utilisateurRepository, PasswordEncoder passwordEncoder) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Utilisateur> findAll() {
        return utilisateurRepository.findAll();
    }

    public Optional<Utilisateur> findById(String id) {
        return utilisateurRepository.findById(id);
    }

    public Optional<Utilisateur> findByMatricule(String matricule) {
        return utilisateurRepository.findByMatricule(matricule);
    }

    public Utilisateur save(Utilisateur utilisateur) {
        String rawPassword = utilisateur.getMotDePasse();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        utilisateur.setMotDePasse(encodedPassword);
        return utilisateurRepository.save(utilisateur);
    }
    

    public void deleteById(String id) {
        utilisateurRepository.deleteById(id);
    }
    public List<Utilisateur> findByRole(String role) {
        return utilisateurRepository.findByRole(role);
    }
    public List<Utilisateur> findByClasseId(String classeId) {
        return utilisateurRepository.findByClasseId(classeId);
    }
    
}
