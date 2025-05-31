package com.ism.absences.service;

import com.ism.absences.entity.Pointage;
import com.ism.absences.repository.PointageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PointageService {

    private final PointageRepository pointageRepository;

    public Pointage enregistrerPointage(String etudiantId, String emailVigile, String etat, Boolean justifiee) {
        Pointage pointage = new Pointage();
        pointage.setEtudiantId(etudiantId);

        // Date du jour à minuit
        LocalDate today = LocalDate.now();
        Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        pointage.setDate(date);

        // Heure précise maintenant
        LocalDateTime now = LocalDateTime.now();
        pointage.setHeure(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));

        pointage.setEtat(etat.toLowerCase()); // "present", "absent", "retard"
        pointage.setCreePar(emailVigile);
        pointage.setJustifiee(justifiee); // Peut être null si non applicable (pour present par ex.)

        return pointageRepository.save(pointage);
    }
}
