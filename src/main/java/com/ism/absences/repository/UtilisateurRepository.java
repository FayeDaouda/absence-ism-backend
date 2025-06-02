package com.ism.absences.repository;

import com.ism.absences.entity.Utilisateur;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UtilisateurRepository extends MongoRepository<Utilisateur, String> {
    Optional<Utilisateur> findByMatricule(String matricule);
    Optional<Utilisateur> findByEmail(String email);
}

   
