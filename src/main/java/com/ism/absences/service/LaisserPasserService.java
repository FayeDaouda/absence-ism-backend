package com.ism.absences.service;

import com.ism.absences.entity.LaisserPasser;
import com.ism.absences.repository.LaisserPasserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaisserPasserService {

    private final LaisserPasserRepository repository;

    public LaisserPasserService(LaisserPasserRepository repository) {
        this.repository = repository;
    }

    public List<LaisserPasser> findAll() {
        return repository.findAll();
    }

    public Optional<LaisserPasser> findById(String id) {
        return repository.findById(id);
    }

    public List<LaisserPasser> findByEtudiantId(String etudiantId) {
        return repository.findByEtudiantId(etudiantId);
    }

    public LaisserPasser save(LaisserPasser laisserPasser) {
        return repository.save(laisserPasser);
    }

    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
