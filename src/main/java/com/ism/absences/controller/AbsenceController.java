package com.ism.absences.controller;

import com.ism.absences.entity.Absence;
import com.ism.absences.repository.AbsenceRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/absences")
@CrossOrigin("*")
public class AbsenceController {

    private final AbsenceRepository absenceRepository;

    public AbsenceController(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    // Ajouter une absence
    @PostMapping
    public Absence enregistrerAbsence(@RequestBody Absence absence) {
        return absenceRepository.save(absence);
    }

    // Lister toutes les absences
    @GetMapping
    public List<Absence> listerAbsences() {
        return absenceRepository.findAll();
    }

    // Lister les absences d'un étudiant donné par id étudiant
    @GetMapping("/etudiant/{etudiantId}")
    public List<Absence> listerAbsencesParEtudiant(@PathVariable String etudiantId) {
        return absenceRepository.findByEtudiantId(etudiantId);
    }

    // Mettre à jour une absence
    @PutMapping("/{id}")
    public ResponseEntity<Absence> mettreAJourAbsence(@PathVariable String id, @RequestBody Absence absence) {
        return absenceRepository.findById(id)
                .map(existing -> {
                    existing.setDate(absence.getDate());
                    existing.setHeure(absence.getHeure());
                    existing.setJustifiee(absence.isJustifiee());
                    existing.setRetard(absence.isRetard());
                    existing.setCreePar(absence.getCreePar());
                    // Mets à jour d'autres champs si besoin
                    Absence updated = absenceRepository.save(existing);
                    return ResponseEntity.ok(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    // Supprimer une absence
    @DeleteMapping("/{id}")
public ResponseEntity<?> supprimerAbsence(@PathVariable String id) {
    return absenceRepository.findById(id)
            .map(absence -> {
                absenceRepository.delete(absence);
                return ResponseEntity.noContent().build();
            }).orElse(ResponseEntity.notFound().build());
}

    }

