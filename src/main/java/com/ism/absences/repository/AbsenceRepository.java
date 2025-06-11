package com.ism.absences.repository;

import com.ism.absences.entity.Absence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceRepository extends MongoRepository<Absence, String> {

    List<Absence> findByEtudiantId(String etudiantId);
    List<Absence> findByCoursId(String coursId);

    // Ajoute cette méthode personnalisée pour ta recherche précise
    List<Absence> findByEtudiantIdAndCoursIdAndDate(String etudiantId, String coursId, LocalDate date);
    boolean existsByEtudiantIdAndCoursIdAndDate(String etudiantId, String coursId, LocalDate date);



    // Tu peux ajouter d'autres méthodes de recherche selon besoin
}
