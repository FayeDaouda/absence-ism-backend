package com.ism.absences.service;

import com.ism.absences.dto.request.PointageRequestDTO;
import com.ism.absences.entity.Absence;
import com.ism.absences.entity.SessionCours;
import com.ism.absences.entity.Utilisateur;
import com.ism.absences.enums.Role;
import com.ism.absences.repository.AbsenceRepository;
import com.ism.absences.repository.SessionCoursRepository;
import com.ism.absences.repository.UtilisateurRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
        try {
            // Recherche de l'étudiant via matricule
            Utilisateur etudiant = utilisateurRepository.findByMatricule(request.getMatricule())
                    .orElseThrow(() -> new IllegalArgumentException("Étudiant introuvable"));

            // Vérification que l'utilisateur est bien un étudiant
            if (etudiant.getRole() != Role.ETUDIANT) {
                return ResponseEntity.badRequest().body("Ce n'est pas un étudiant");
            }

            LocalDateTime now = LocalDateTime.now();
            String classeId = etudiant.getClasseId();

            // Récupération des sessions de cours pour la classe et la date du jour
            List<SessionCours> sessions = sessionCoursRepository.findByClasseIdAndDateCours(classeId, now.toLocalDate());

            // Recherche du cours en cours d'après l'heure actuelle
            SessionCours session = sessions.stream()
                    .filter(sc -> {
                        LocalTime currentTime = now.toLocalTime();
                        return !currentTime.isBefore(sc.getHeureDebut()) && !currentTime.isAfter(sc.getHeureFin());
                    })
                    .findFirst()
                    .orElse(null);

            if (session == null) {
                return ResponseEntity.badRequest().body("Aucun cours en cours pour la classe de cet étudiant.");
            }

            // Vérification qu'une absence n'a pas déjà été enregistrée pour cet étudiant, ce cours et cette date
            boolean dejaPointe = !absenceRepository.findByEtudiantIdAndCoursIdAndDate(
                    etudiant.getId(), session.getId(), now.toLocalDate()).isEmpty();

            if (dejaPointe) {
                return ResponseEntity.badRequest().body("Absence déjà enregistrée pour cet étudiant et ce cours à cette date");
            }

            // Détermination si l'étudiant est en retard (plus de 10 min après début cours)
            boolean enRetard = now.toLocalTime().isAfter(session.getHeureDebut().plusMinutes(10));

            // Création et sauvegarde de l'absence
            Absence absence = new Absence();
            absence.setEtudiantId(etudiant.getId());
            absence.setCoursId(session.getId());
            absence.setDate(now.toLocalDate());
            absence.setHeureDebut(session.getHeureDebut());
            absence.setHeureFin(session.getHeureFin());
            absence.setStatut(enRetard ? "RETARD" : "PRESENT");

            Absence savedAbsence = absenceRepository.save(absence);

            return ResponseEntity.ok(savedAbsence);

        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erreur serveur: " + e.getMessage());
        }
    }
}
