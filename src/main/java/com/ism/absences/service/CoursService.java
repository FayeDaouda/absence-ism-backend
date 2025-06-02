package com.ism.absences.service;

import com.ism.absences.entity.Cours;
import com.ism.absences.repository.CoursRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursService {

    private final CoursRepository coursRepository;

    public CoursService(CoursRepository coursRepository) {
        this.coursRepository = coursRepository;
    }

    public List<Cours> findAll() {
        return coursRepository.findAll();
    }

    public Optional<Cours> findById(String id) {
        return coursRepository.findById(id);
    }

    public List<Cours> findByClasseId(String classeId) {
        return coursRepository.findByClasseId(classeId);
    }

    public List<Cours> findByProfesseur(String professeur) {
        return coursRepository.findByProfesseur(professeur);
    }

    public Cours save(Cours cours) {
        return coursRepository.save(cours);
    }

    public void deleteById(String id) {
        coursRepository.deleteById(id);
    }
}
