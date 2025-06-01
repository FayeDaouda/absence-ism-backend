package com.ism.absences.service;

import com.ism.absences.entity.Pointage;
import com.ism.absences.repository.PointageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PointageService {

    private final PointageRepository pointageRepository;

    public Pointage enregistrerPointage(String etudiantId, String emailVigile, String etat, Boolean justifiee) {
        Pointage pointage = new Pointage();
        pointage.setEtudiantId(etudiantId);

        LocalDate today = LocalDate.now();
        Date date = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        pointage.setDate(date);

        LocalDateTime now = LocalDateTime.now();
        pointage.setHeure(Date.from(now.atZone(ZoneId.systemDefault()).toInstant()));

        pointage.setEtat(etat.toLowerCase());
        pointage.setCreePar(emailVigile);
        pointage.setJustifiee(justifiee);

        return pointageRepository.save(pointage);
    }

    public List<Pointage> getPointagesDuJour() {
        LocalDate today = LocalDate.now();
        Date startOfDay = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endOfDay = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());

        return pointageRepository.findByDateBetween(startOfDay, endOfDay);
    }
    
    public List<Pointage> getAllPointages() {
        return pointageRepository.findAll();
    }

    public List<Pointage> getPointagesByEtudiantId(String etudiantId) {
        return pointageRepository.findByEtudiantId(etudiantId);
    }
    
}
