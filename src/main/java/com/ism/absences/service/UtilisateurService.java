package com.ism.absences.service;

import com.ism.absences.entity.Utilisateur;
import com.ism.absences.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
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
        return utilisateurRepository.save(utilisateur);
    }

    public void deleteById(String id) {
        utilisateurRepository.deleteById(id);
    }
}
