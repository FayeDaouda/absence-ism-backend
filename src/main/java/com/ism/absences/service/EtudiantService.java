package com.ism.absences.service;

import com.ism.absences.entity.Etudiant;
import com.ism.absences.repository.EtudiantRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
