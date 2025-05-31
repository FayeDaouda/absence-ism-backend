package com.ism.absences.repository;

import com.ism.absences.entity.Etudiant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EtudiantRepository extends MongoRepository<Etudiant, String> {
    List<Etudiant> findAllByMatricule(String matricule);

}
