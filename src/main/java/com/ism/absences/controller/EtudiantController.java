package com.ism.absences.controller;

import com.ism.absences.entity.Etudiant;
import com.ism.absences.service.EtudiantService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin("*")
public class EtudiantController {

    private final EtudiantService service;

    public EtudiantController(EtudiantService service) {
        this.service = service;
    }

    // Ajouter un étudiant
    @PostMapping
    public Etudiant ajouterEtudiant(@RequestBody Etudiant etudiant) {
        return service.ajouterEtudiant(etudiant);
    }

    // Lister tous les étudiants ou rechercher par matricule
    @GetMapping
public List<Etudiant> listerEtudiants(@RequestParam(required = false) String matricule) {
    if (matricule != null) {
        Optional<Etudiant> etudiant = service.rechercherParMatricule(matricule);
        return etudiant.map(List::of).orElse(List.of());
    }
    return service.listerEtudiants();
}

    // Récupérer un étudiant par son ID (ici String, adapte si besoin)
    @GetMapping("/{id}")
    public ResponseEntity<Etudiant> getEtudiantById(@PathVariable String id) {
        Optional<Etudiant> etudiantOpt = service.getEtudiantById(id);
        return etudiantOpt
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Mettre à jour un étudiant par son ID
    @PutMapping("/{id}")
    public ResponseEntity<Etudiant> mettreAJourEtudiant(@PathVariable String id, @RequestBody Etudiant etudiant) {
        Optional<Etudiant> updatedEtudiant = service.mettreAJourEtudiant(id, etudiant);
        return updatedEtudiant
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Supprimer un étudiant par son ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerEtudiant(@PathVariable String id) {
        boolean deleted = service.supprimerEtudiant(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
