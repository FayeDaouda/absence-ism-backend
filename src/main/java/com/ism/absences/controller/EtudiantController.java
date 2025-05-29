package com.ism.absences.controller;

import com.ism.absences.entity.Etudiant;
import com.ism.absences.service.EtudiantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin("*")
public class EtudiantController {

    private final EtudiantService service;

    public EtudiantController(EtudiantService service) {
        this.service = service;
    }

    @PostMapping
    public Etudiant ajouterEtudiant(@RequestBody Etudiant etudiant) {
        return service.ajouterEtudiant(etudiant);
    }

    @GetMapping
    public List<Etudiant> listerEtudiants() {
        return service.listerEtudiants();
    }
}
