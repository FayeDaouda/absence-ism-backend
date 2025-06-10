package com.ism.absences.repository;

import com.ism.absences.entity.Justification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JustificationRepository extends MongoRepository<Justification, String> {
    List<Justification> findByEtudiantId(String etudiantId);
    List<Justification> findByAbsenceId(String absenceId);
    List<Justification> findByStatut(String statut);
}
