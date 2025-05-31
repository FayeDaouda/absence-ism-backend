package com.ism.absences.controller;

import com.ism.absences.entity.Etudiant;
import com.ism.absences.service.EtudiantService;

import org.springframework.http.ResponseEntity;
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
    public List<Etudiant> listerEtudiants(@RequestParam(required = false) String matricule) {
        if (matricule != null) {
            return service.rechercherParMatricule(matricule);
        }
        return service.listerEtudiants();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable String id) {
        return service.getEtudiantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> mettreAJourEtudiant(@PathVariable String id, @RequestBody Etudiant etudiant) {
        return service.mettreAJourEtudiant(id, etudiant)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEtudiant(@PathVariable String id) {
        boolean deleted = service.supprimerEtudiant(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
