package com.ism.absences.repository;

import com.ism.absences.entity.LaisserPasser;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LaisserPasserRepository extends MongoRepository<LaisserPasser, String> {
    List<LaisserPasser> findByEtudiantId(String etudiantId);
}
