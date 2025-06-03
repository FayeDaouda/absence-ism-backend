package com.ism.absences.repository;

import com.ism.absences.entity.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CoursRepository extends MongoRepository<Cours, String> {
    List<Cours> findByClasseId(String classeId);
    List<Cours> findByProfesseur(String professeur);
    List<Cours> findByClasseIdAndDateHeureDebutBetween(String classeId, LocalDateTime debut, LocalDateTime fin);
}
