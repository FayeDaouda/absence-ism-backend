package com.ism.absences.repository;

import com.ism.absences.entity.Pointage;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

public interface PointageRepository extends MongoRepository<Pointage, String> {

    // Récupérer tous les pointages d'un vigile entre deux dates
    List<Pointage> findByCreeParAndDateBetween(String creePar, Date start, Date end);

    List<Pointage> findByDateBetween(Date start, Date end);

    List<Pointage> findByEtudiantId(String etudiantId);

    


}
