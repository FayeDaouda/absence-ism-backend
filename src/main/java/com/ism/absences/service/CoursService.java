package com.ism.absences.service;

import com.ism.absences.entity.Cours;
import com.ism.absences.entity.Utilisateur;
import com.ism.absences.repository.CoursRepository;
import com.ism.absences.repository.UtilisateurRepository;
import org.springframework.stereotype.Service;
import com.ism.absences.enums.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CoursService {

    private final CoursRepository coursRepository;
    private final UtilisateurRepository utilisateurRepository;

    public CoursService(CoursRepository coursRepository, UtilisateurRepository utilisateurRepository) {
        this.coursRepository = coursRepository;
        this.utilisateurRepository = utilisateurRepository;
    }

    public List<Cours> findAll() {
        return coursRepository.findAll();
    }

    public Optional<Cours> findById(String id) {
        return coursRepository.findById(id);
    }

    public List<Cours> findByClasseId(String classeId) {
        return coursRepository.findByClasseId(classeId);
    }

    public List<Cours> findByProfesseur(String professeur) {
        return coursRepository.findByProfesseur(professeur);
    }

    public Cours save(Cours cours) {
        return coursRepository.save(cours);
    }

    public void deleteById(String id) {
        coursRepository.deleteById(id);
    }

    public List<Cours> findCoursDeLaSemaine(String classeId) {
        LocalDate today = LocalDate.now();
        LocalDate startOfWeek = today.with(java.time.DayOfWeek.MONDAY);
        LocalDate endOfWeek = today.with(java.time.DayOfWeek.SUNDAY);

        LocalDateTime startDateTime = startOfWeek.atStartOfDay();
        LocalDateTime endDateTime = endOfWeek.atTime(LocalTime.MAX);

        return coursRepository.findByClasseIdAndDateHeureDebutBetween(classeId, startDateTime, endDateTime);
    }

    public List<Cours> getCoursDuJourParMatricule(String matricule) {
        Optional<Utilisateur> utilisateurOpt = utilisateurRepository.findByMatricule(matricule);

        if (utilisateurOpt.isEmpty() || utilisateurOpt.get().getRole() != Role.ETUDIANT) {
            return Collections.emptyList(); // ou lancer une exception personnalisée
        }

        Utilisateur etudiant = utilisateurOpt.get();
        String classeId = etudiant.getClasseId();

        LocalDate aujourdhui = LocalDate.now();
        return coursRepository.findByClasseIdAndDate(classeId, aujourdhui);
    }
}
