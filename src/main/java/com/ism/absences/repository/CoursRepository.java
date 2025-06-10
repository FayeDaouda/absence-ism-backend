package com.ism.absences.repository;

import com.ism.absences.entity.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CoursRepository extends MongoRepository<Cours, String> {

    List<Cours> findByClasseId(String classeId);

    List<Cours> findByProfesseur(String professeur);

    List<Cours> findByDateHeureDebutBetween(LocalDateTime start, LocalDateTime end);


    List<Cours> findByClasseIdAndDateHeureDebutBetween(String classeId, LocalDateTime debut, LocalDateTime fin);

    // Méthode par défaut pour récupérer les cours du jour par classeId
    default List<Cours> findCoursAujourdhuiByClasseId(String classeId) {
        LocalDateTime startOfDay = LocalDateTime.now().toLocalDate().atStartOfDay();
        LocalDateTime endOfDay = startOfDay.plusHours(23).plusMinutes(59).plusSeconds(59);
        return findByClasseIdAndDateHeureDebutBetween(classeId, startOfDay, endOfDay);
    }
    // Méthode pour trouver les cours par classeId et par date (tout le jour)
    default List<Cours> findByClasseIdAndDate(String classeId, LocalDate date) {
        LocalDateTime startOfDay = date.atStartOfDay();
        LocalDateTime endOfDay = date.atTime(23, 59, 59);
        return findByClasseIdAndDateHeureDebutBetween(classeId, startOfDay, endOfDay);
    }
}
