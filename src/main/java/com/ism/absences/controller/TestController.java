package com.ism.absences.controller;

import com.ism.absences.entity.Etudiant;
import com.ism.absences.repository.EtudiantRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test")
public class TestController {

    private final EtudiantRepository etudiantRepository;

    public TestController(EtudiantRepository etudiantRepository) {
        this.etudiantRepository = etudiantRepository;
    }

    @GetMapping("/etudiants")
    public List<Etudiant> getAll() {
        return etudiantRepository.findAll();
    }
}
