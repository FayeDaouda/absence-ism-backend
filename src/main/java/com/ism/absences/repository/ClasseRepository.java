package com.ism.absences.repository;

import com.ism.absences.entity.Classe;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClasseRepository extends MongoRepository<Classe, String> {
    Classe findByNom(String nom);
}
