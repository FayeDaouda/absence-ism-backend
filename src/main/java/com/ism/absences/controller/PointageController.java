package com.ism.absences.controller;

import com.ism.absences.entity.Pointage;
import com.ism.absences.service.PointageService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pointages")
public class PointageController {

    private final PointageService pointageService;

    public PointageController(PointageService pointageService) {
        this.pointageService = pointageService;
    }

    @PostMapping
    public ResponseEntity<Pointage> ajouterPointage(@RequestBody PointageRequest req) {
        Pointage pointage = pointageService.enregistrerPointage(
                req.getMatricule(),
                req.getEmailVigile(),
                req.getEtat(),
                req.getJustifiee()
        );
        return ResponseEntity.ok(pointage);
    }

    @GetMapping
    public ResponseEntity<List<Pointage>> getAllPointages() {
        List<Pointage> pointages = pointageService.getAllPointages();
        return ResponseEntity.ok(pointages);
    }

    @GetMapping("/aujourdhui")
    public ResponseEntity<List<Pointage>> getPointagesDuJour() {
        List<Pointage> pointages = pointageService.getPointagesDuJour();
        return ResponseEntity.ok(pointages);
    }

    @GetMapping("/etudiant/{etudiantId}")
public ResponseEntity<List<Pointage>> getPointagesByEtudiantId(@PathVariable String etudiantId) {
    List<Pointage> pointages = pointageService.getPointagesByEtudiantId(etudiantId);
    return ResponseEntity.ok(pointages);
}


    public static class PointageRequest {
        private String matricule;
        private String emailVigile;
        private String etat;
        private Boolean justifiee;

        // getters & setters...
        public String getMatricule() { return matricule; }
        public void setMatricule(String matricule) { this.matricule = matricule; }
        public String getEmailVigile() { return emailVigile; }
        public void setEmailVigile(String emailVigile) { this.emailVigile = emailVigile; }
        public String getEtat() { return etat; }
        public void setEtat(String etat) { this.etat = etat; }
        public Boolean getJustifiee() { return justifiee; }
        public void setJustifiee(Boolean justifiee) { this.justifiee = justifiee; }
    }
}
