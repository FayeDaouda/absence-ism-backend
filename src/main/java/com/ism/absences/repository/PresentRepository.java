package com.ism.absences.repository;

import com.ism.absences.entity.Present;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentRepository extends MongoRepository<Present, String> {
    List<Present> findByEtudiantId(String etudiantId);

    List<Present> findByEtudiantIdAndDateBetween(String etudiantId, LocalDate startDate, LocalDate endDate);

    List<Present> findByDate(LocalDate date);
}
