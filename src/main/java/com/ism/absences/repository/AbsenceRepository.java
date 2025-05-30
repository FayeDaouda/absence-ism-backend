package com.ism.absences.repository;

import com.ism.absences.entity.Absence;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AbsenceRepository extends MongoRepository<Absence, String> {}
