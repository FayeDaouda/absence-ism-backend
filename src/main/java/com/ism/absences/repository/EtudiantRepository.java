package com.ism.absences.repository;

import com.ism.absences.entity.Etudiant;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EtudiantRepository extends MongoRepository<Etudiant, String> {
    // Méthodes personnalisées si besoin
}
