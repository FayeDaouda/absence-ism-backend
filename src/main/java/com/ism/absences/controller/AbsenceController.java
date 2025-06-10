package com.ism.absences.controller;

import com.ism.absences.entity.Absence;
import com.ism.absences.service.AbsenceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/absences")
@CrossOrigin(origins = "*")
public class AbsenceController {

    private final AbsenceService absenceService;

    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping
    public List<Absence> getAllAbsences() {
        return absenceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Absence> getAbsenceById(@PathVariable String id) {
        Optional<Absence> absence = absenceService.findById(id);
        return absence.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/etudiant/{etudiantId}")
    public List<Absence> getAbsencesByEtudiantId(@PathVariable String etudiantId) {
        return absenceService.findByEtudiantId(etudiantId);
    }

    @PostMapping
    public Absence createAbsence(@RequestBody Absence absence) {
        return absenceService.save(absence);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Absence> updateAbsence(@PathVariable String id, @RequestBody Absence absenceDetails) {
        Optional<Absence> absenceOpt = absenceService.findById(id);
        if (absenceOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Absence absence = absenceOpt.get();
        absence.setDate(absenceDetails.getDate());
        absence.setHeureDebut(absenceDetails.getHeureDebut());
        absence.setHeureFin(absenceDetails.getHeureFin());
        absence.setStatut(absenceDetails.getStatut());
        absence.setEtudiantId(absenceDetails.getEtudiantId());
        absence.setVigileId(absenceDetails.getVigileId());
        absence.setCoursId(absenceDetails.getCoursId());
        absence.setJustificationId(absenceDetails.getJustificationId());

        Absence updated = absenceService.save(absence);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbsence(@PathVariable String id) {
        absenceService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
