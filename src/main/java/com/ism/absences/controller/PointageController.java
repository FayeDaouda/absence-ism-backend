package com.ism.absences.controller;

import com.ism.absences.entity.Absence;
import com.ism.absences.entity.Etudiant;
import com.ism.absences.repository.AbsenceRepository;
import com.ism.absences.repository.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/pointages")
@RequiredArgsConstructor
public class PointageController {

    private final EtudiantRepository etudiantRepository;
    private final AbsenceRepository absenceRepository;

    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("Pointage API is alive");
    }

    @PostMapping
public ResponseEntity<?> pointerEtudiant(@RequestBody Map<String, String> payload) {
    String matricule = payload.get("matricule");
    String emailVigile = payload.get("emailVigile");

    if (matricule == null || matricule.isEmpty() || emailVigile == null || emailVigile.isEmpty()) {
        return ResponseEntity.badRequest().body("matricule et emailVigile sont requis");
    }

    Optional<Etudiant> etudiantOpt = etudiantRepository.findByMatricule(matricule);
    if (etudiantOpt.isEmpty()) {
        return ResponseEntity.badRequest().body("Matricule non trouvé");
    }

    Etudiant etudiant = etudiantOpt.get();

    Absence absence = new Absence();
    absence.setEtudiantId(etudiant.getId());
    absence.setDate(LocalDate.now());
    absence.setHeure(LocalTime.now());
    absence.setJustifiee(false);
    absence.setRetard(LocalTime.now().isAfter(LocalTime.of(8, 30)));
    absence.setCreePar(emailVigile);

    Absence savedAbsence = absenceRepository.save(absence);
    return ResponseEntity.ok(savedAbsence);
}

}
