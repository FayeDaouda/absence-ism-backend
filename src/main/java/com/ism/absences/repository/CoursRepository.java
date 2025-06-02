package com.ism.absences.repository;

import com.ism.absences.entity.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CoursRepository extends MongoRepository<Cours, String> {
    List<Cours> findByClasseId(String classeId);
    List<Cours> findByProfesseur(String professeur);
}
