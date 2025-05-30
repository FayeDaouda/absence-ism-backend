package com.ism.absences.repository;

import com.ism.absences.entity.Absence;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceRepository extends MongoRepository<Absence, String> {
    // Tu peux ajouter des méthodes spécifiques ici si besoin, par exemple:
    // List<Absence> findByEtudiantId(String etudiantId);
     // Trouver toutes les absences d'un étudiant par son id
    List<Absence> findByEtudiantId(String etudiantId);
    
    // Trouver les absences d'un étudiant sur une période donnée
    List<Absence> findByEtudiantIdAndDateBetween(String etudiantId, LocalDate startDate, LocalDate endDate);
    
    // Trouver les absences justifiées ou non
    List<Absence> findByJustifiee(boolean justifiee);
    
    // Trouver les absences par date
    List<Absence> findByDate(LocalDate date);

}
