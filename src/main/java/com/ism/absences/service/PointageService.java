package com.ism.absences.service;


import com.ism.absences.dto.request.PointageRequestDTO;
import com.ism.absences.model.Absence;
import com.ism.absences.model.SessionCours;
import com.ism.absences.model.Utilisateur;
import com.ism.absences.repository.AbsenceRepository;
import com.ism.absences.repository.SessionCoursRepository;
import com.ism.absences.repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PointageService {

    private final UtilisateurRepository utilisateurRepository;
    private final SessionCoursRepository sessionCoursRepository;
    private final AbsenceRepository absenceRepository;

    public PointageService(UtilisateurRepository utilisateurRepository,
                           SessionCoursRepository sessionCoursRepository,
                           AbsenceRepository absenceRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.sessionCoursRepository = sessionCoursRepository;
        this.absenceRepository = absenceRepository;
    }

    public ResponseEntity<?> pointerAutomatiquement(PointageRequestDTO request) {
        Optional<Utilisateur> optionalEtudiant = utilisateurRepository.findByMatricule(request.getMatricule());

        if (optionalEtudiant.isEmpty()) {
            return ResponseEntity.badRequest().body("Étudiant introuvable");
        }

        Utilisateur etudiant = optionalEtudiant.get();

        if (!etudiant.getRole().equalsIgnoreCase("ETUDIANT")) {
            return ResponseEntity.badRequest().body("Ce n'est pas un étudiant");
        }

        LocalDateTime now = LocalDateTime.now();

        // On cherche un cours en cours actuellement
        Optional<SessionCours> coursActuel = sessionCoursRepository
                .findByClasseIdAndDate(etudiant.getClasse().getId(), now.toLocalDate())
                .stream()
                .filter(sc -> now.isAfter(sc.getHeureDebut()) && now.isBefore(sc.getHeureFin()))
                .findFirst();

        if (coursActuel.isEmpty()) {
            return ResponseEntity.badRequest().body("Aucun cours en cours pour la classe de cet étudiant.");
        }

        SessionCours session = coursActuel.get();

        // Déterminer si l'étudiant est en retard (> 10 minutes après le début)
        boolean enRetard = now.isAfter(session.getHeureDebut().plusMinutes(10));

        Absence absence = new Absence();
        absence.setEtudiant(etudiant);
        absence.setSessionCours(session);
        absence.setStatut(enRetard ? "RETARD" : "PRESENT");
        absence.setDateHeurePointage(now);

        absenceRepository.save(absence);

        return ResponseEntity.ok("Étudiant pointé automatiquement : " + absence.getStatut());
    }
}
