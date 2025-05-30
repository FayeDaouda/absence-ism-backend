package com.ism.absences.repository;

import com.ism.absences.entity.Etudiant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EtudiantRepository extends MongoRepository<Etudiant, String> {
    Optional<Etudiant> findByMatricule(String matricule);
}
