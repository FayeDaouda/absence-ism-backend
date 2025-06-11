package com.ism.absences.service;

import com.ism.absences.entity.Absence;
import com.ism.absences.entity.Cours;
import com.ism.absences.entity.Utilisateur;
import com.ism.absences.repository.AbsenceRepository;
import com.ism.absences.repository.CoursRepository;
import com.ism.absences.repository.UtilisateurRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AbsenceSchedulerService {

    private final CoursRepository coursRepository;
    private final AbsenceRepository absenceRepository;
    private final UtilisateurRepository utilisateurRepository;

    public AbsenceSchedulerService(CoursRepository coursRepository,
                                   AbsenceRepository absenceRepository,
                                   UtilisateurRepository utilisateurRepository) {
        this.coursRepository = coursRepository;
        this.absenceRepository = absenceRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    // Tous les jours à 2h du matin
    @Scheduled(cron = "0 25 2 * * *")

    public void markAbsentForNonPointedStudents() {
        System.out.println("📅 Tâche planifiée : marquage automatique des absents");

        LocalDate today = LocalDate.now();

        // 1. Récupérer les cours du jour
        List<Cours> coursDuJour = coursRepository.findByDateHeureDebutBetween(
                today.atStartOfDay(), today.plusDays(1).atStartOfDay());

        for (Cours cours : coursDuJour) {
            String coursId = cours.getId();
            String classeId = cours.getClasseId();

            // 2. Récupérer les utilisateurs étudiants de cette classe
            List<Utilisateur> etudiants = utilisateurRepository.findByRoleAndClasseId("ETUDIANT", classeId);

            // 3. Récupérer les absences déjà enregistrées pour ce cours
            List<Absence> absences = absenceRepository.findByCoursId(coursId);

            Set<String> dejaPointes = absences.stream()
                    .filter(a -> a.getStatut().equalsIgnoreCase("present") || a.getStatut().equalsIgnoreCase("retard"))
                    .map(Absence::getEtudiantId)
                    .collect(Collectors.toSet());

            // 4. Créer une absence pour ceux qui ne sont pas pointés
            for (Utilisateur etudiant : etudiants) {
                if (!dejaPointes.contains(etudiant.getId())) {
                    Absence absence = new Absence();
                    absence.setEtudiantId(etudiant.getId());
                    absence.setCoursId(coursId);
                    absence.setDate(today);
                    absence.setHeureDebut(cours.getDateHeureDebut().toLocalTime());
                    absence.setHeureFin(cours.getDateHeureFin().toLocalTime());
                    absence.setStatut("absent");
                    absence.setJustificationId(null); 
                    absence.setVigileId(null); 

                    absenceRepository.save(absence);
                    System.out.println("🚫 Absent auto : " + etudiant.getNom() + " - cours " + coursId);
                }
            }
        }

        System.out.println("✅ Fin de la tâche planifiée");
    }
}
