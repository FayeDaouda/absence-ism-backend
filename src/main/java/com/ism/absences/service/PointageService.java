package com.ism.absences.service;

import com.ism.absences.dto.request.PointageRequestDTO;
import com.ism.absences.entity.Absence;
import com.ism.absences.entity.SessionCours;
import com.ism.absences.entity.Utilisateur;
import com.ism.absences.repository.AbsenceRepository;
import com.ism.absences.repository.SessionCoursRepository;
import com.ism.absences.repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
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

        List<SessionCours> sessions = sessionCoursRepository.findByClasseIdAndDateCours(etudiant.getClasse().getId(), now.toLocalDate());

        Optional<SessionCours> coursActuel = sessions.stream()
                .filter(sc -> {
                    LocalTime currentTime = now.toLocalTime();
                    return currentTime.isAfter(sc.getHeureDebut()) && currentTime.isBefore(sc.getHeureFin());
                })
                .findFirst();

        if (coursActuel.isEmpty()) {
            return ResponseEntity.badRequest().body("Aucun cours en cours pour la classe de cet étudiant.");
        }

        SessionCours session = coursActuel.get();

        boolean enRetard = now.toLocalTime().isAfter(session.getHeureDebut().plusMinutes(10));

        Absence absence = new Absence();
        absence.setEtudiant(etudiant);
        absence.setSessionCours(session);
        absence.setStatut(enRetard ? "RETARD" : "PRESENT");
        absence.setDateHeurePointage(now);

        absenceRepository.save(absence);

        return ResponseEntity.ok("Étudiant pointé automatiquement : " + absence.getStatut());
    }
}
