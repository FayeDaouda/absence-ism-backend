package com.ism.absences.repository;

import com.ism.absences.entity.SessionCours;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface SessionCoursRepository extends MongoRepository<SessionCours, String> {

    // Méthode pour trouver les sessions d'une classe à une date donnée
    List<SessionCours> findByClasseIdAndDateCours(String classeId, LocalDate dateCours);
}
