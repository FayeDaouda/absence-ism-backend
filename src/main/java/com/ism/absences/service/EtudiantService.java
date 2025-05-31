package com.ism.absences.service;

import com.ism.absences.entity.Etudiant;
import com.ism.absences.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class EtudiantService {
    private final EtudiantRepository repository;

    

    public EtudiantService(EtudiantRepository repository) {
        this.repository = repository;
    }

    public Etudiant ajouterEtudiant(Etudiant e) {
        return repository.save(e);
    }

    public List<Etudiant> listerEtudiants() {
        return repository.findAll();
    }

    public Optional<Etudiant> getEtudiantById(String id) {
        return repository.findById(id);
    }

    public Optional<Etudiant> mettreAJourEtudiant(String id, Etudiant etudiant) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setNom(etudiant.getNom());
                    existing.setPrenom(etudiant.getPrenom());
                    existing.setMatricule(etudiant.getMatricule());
                    // mets à jour d'autres champs si besoin
                    return repository.save(existing);
                });
    }

    public boolean supprimerEtudiant(String id) {
        return repository.findById(id)
                .map(e -> {
                    repository.delete(e);
                    return true;
                }).orElse(false);
    }
    // Nouvelle méthode pour rechercher par matricule
    // Nouvelle méthode pour rechercher par matricule
    public Optional<Etudiant> rechercherParMatricule(String matricule) {
        return repository.findByMatricule(matricule);
    }
}
