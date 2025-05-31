package com.ism.absences.controller;

import com.ism.absences.entity.Pointage;
import com.ism.absences.service.PointageService;
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
        // etat attend "present", "absent" ou "retard"
        Pointage pointage = pointageService.enregistrerPointage(
                req.getMatricule(),
                req.getEmailVigile(),
                req.getEtat(),
                req.getJustifiee()
        );
        return ResponseEntity.ok(pointage);
    }

    public static class PointageRequest {
        private String matricule;
        private String emailVigile;
        private String etat;       // "present", "absent", "retard"
        private Boolean justifiee; // null si non applicable

        // getters & setters
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
