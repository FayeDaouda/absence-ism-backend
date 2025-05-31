package com.ism.absences.repository;

import com.ism.absences.entity.Absence;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AbsenceRepository extends MongoRepository<Absence, String> {

    List<Absence> findByMatricule(String matricule);

}
