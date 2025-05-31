package com.ism.absences.repository;

import com.ism.absences.entity.Pointage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PointageRepository extends MongoRepository<Pointage, String> {
    // Tu peux aussi ajouter des méthodes personnalisées ici si besoin
}
