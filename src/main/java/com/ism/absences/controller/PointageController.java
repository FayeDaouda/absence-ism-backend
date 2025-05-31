package com.ism.absences.controller;

import com.ism.absences.entity.Absence;
import com.ism.absences.entity.Present;
import com.ism.absences.repository.AbsenceRepository;
import com.ism.absences.repository.PresentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/api")
public class PointageController {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private PresentRepository presentRepository;

    @PostMapping("/absences")
    public ResponseEntity<?> ajouterAbsence(@RequestBody PointageRequest req) {
        Absence absence = new Absence();
        absence.setEtudiantId(req.getMatricule());
        absence.setCreePar(req.getEmailVigile());
        absence.setDate(LocalDate.now());
        absence.setHeure(LocalTime.now());
        absence.setJustifiee(false);
        absence.setRetard(false);
        Absence saved = absenceRepository.save(absence);
        return ResponseEntity.ok(saved);
    }

    @PostMapping("/presents")
    public ResponseEntity<?> ajouterPresent(@RequestBody PointageRequest req) {
        Present present = new Present();
        present.setEtudiantId(req.getMatricule());
        present.setCreePar(req.getEmailVigile());
        present.setDate(LocalDate.now());
        present.setHeure(LocalTime.now());
        Present saved = presentRepository.save(present);
        return ResponseEntity.ok(saved);
    }

    public static class PointageRequest {
        private String matricule;
        private String emailVigile;

        public String getMatricule() {
            return matricule;
        }

        public void setMatricule(String matricule) {
            this.matricule = matricule;
        }

        public String getEmailVigile() {
            return emailVigile;
        }

        public void setEmailVigile(String emailVigile) {
            this.emailVigile = emailVigile;
        }
    }
}
