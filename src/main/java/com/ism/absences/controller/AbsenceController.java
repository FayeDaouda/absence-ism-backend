package com.ism.absences.controller;

import com.ism.absences.entity.Absence;
import com.ism.absences.repository.AbsenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/absences")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AbsenceController {

    private final AbsenceRepository absenceRepository;

    // Récupérer toutes les absences d’un étudiant par matricule
    @GetMapping("/etudiant/{matricule}")
    public ResponseEntity<List<Absence>> getAbsencesByMatricule(@PathVariable String matricule) {
        List<Absence> absences = absenceRepository.findByMatricule(matricule);
        return ResponseEntity.ok(absences);
    }

    // Ajouter une nouvelle absence
    @PostMapping
    public ResponseEntity<Absence> addAbsence(@RequestBody Absence absence) {
        Absence savedAbsence = absenceRepository.save(absence);
        return ResponseEntity.ok(savedAbsence);
    }

    // Justifier une absence par ID
    @PutMapping("/justifier/{id}")
    public ResponseEntity<Absence> justifyAbsence(@PathVariable String id, @RequestBody(required = false) String motif) {
        return absenceRepository.findById(id)
                .map(absence -> {
                    absence.setJustifie(true);
                    absence.setMotif(motif);
                    Absence updated = absenceRepository.save(absence);
                    return ResponseEntity.ok(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
