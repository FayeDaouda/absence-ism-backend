package com.ism.absences.service;

import com.ism.absences.entity.Classe;
import com.ism.absences.repository.ClasseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClasseService {

    private final ClasseRepository classeRepository;

    public ClasseService(ClasseRepository classeRepository) {
        this.classeRepository = classeRepository;
    }

    public List<Classe> findAll() {
        return classeRepository.findAll();
    }

    public Optional<Classe> findById(String id) {
        return classeRepository.findById(id);
    }

    public Classe findByNom(String nom) {
        return classeRepository.findByNom(nom);
    }

    public Classe save(Classe classe) {
        return classeRepository.save(classe);
    }

    public void deleteById(String id) {
        classeRepository.deleteById(id);
    }
}
