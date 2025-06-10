package com.ism.absences.controller;

import com.ism.absences.dto.request.PointageRequestDTO;
import com.ism.absences.entity.Absence;
import com.ism.absences.entity.Cours;
import com.ism.absences.entity.Utilisateur;
import com.ism.absences.repository.AbsenceRepository;
import com.ism.absences.repository.CoursRepository;
import com.ism.absences.repository.UtilisateurRepository;
import com.ism.absences.service.AbsenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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


    @RestController
@RequestMapping("/api/pointages")
public class PointageController {

    @Autowired
    private UtilisateurRepository utilisateurRepository; // Pour chercher l’étudiant par matricule

    @Autowired
    private CoursRepository coursRepository; // Pour récupérer les cours du jour

    @Autowired
    private AbsenceRepository absenceRepository; // Pour enregistrer le pointage

    @PostMapping
    public ResponseEntity<?> pointerEtudiant(@RequestBody PointageRequestDTO request) {
        String matricule = request.getMatricule();
        String vigileId = request.getVigileId();

        // 1. Chercher l’étudiant
        Optional<Utilisateur> etudiantOpt = utilisateurRepository.findByMatricule(matricule);
        if (etudiantOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Étudiant non trouvé");
        }
        Utilisateur etudiant = etudiantOpt.get();

        // 2. Chercher les cours du jour de sa classe
        List<Cours> coursDuJour = coursRepository.findCoursAujourdhuiByClasseId(etudiant.getClasseId());
        if (coursDuJour.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Aucun cours trouvé aujourd’hui pour cet étudiant");
        }

        // 3. Pour chaque cours, comparer heure actuelle et définir statut
        LocalTime now = LocalTime.now();

        List<Absence> absencesPointe = new ArrayList<>();
        for (Cours cours : coursDuJour) {
            String statut;
            if (now.isBefore(cours.getDateHeureDebut().toLocalTime())) {
                statut = "ABSENT"; // Avant l’heure, pas encore arrivé
            } else if (now.isAfter(cours.getDateHeureDebut().toLocalTime()) && now.isBefore(cours.getDateHeureDebut().toLocalTime().plusMinutes(15))) {
                statut = "PRESENT"; // Arrivé à l’heure ou dans la tolérance de 15min
            } else {
                statut = "RETARD"; // Arrivé après 15 minutes
            }

            // 4. Créer et enregistrer l’absence
            Absence absence = new Absence();
            absence.setDate(LocalDate.now());
            absence.setHeureDebut(cours.getDateHeureDebut().toLocalTime());
            absence.setHeureFin(cours.getDateHeureFin().toLocalTime());
            absence.setStatut(statut);
            absence.setEtudiantId(etudiant.getId());
            absence.setVigileId(vigileId);
            absence.setCoursId(cours.getId());

            absenceRepository.save(absence);
            absencesPointe.add(absence);
        }

        return ResponseEntity.ok(absencesPointe);
    }
}

}
