package com.ism.absences.service;

import com.ism.absences.entity.Absence;
import com.ism.absences.entity.Present;
import com.ism.absences.repository.AbsenceRepository;
import com.ism.absences.repository.PresentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class PointageService {

    @Autowired
    private AbsenceRepository absenceRepository;

    @Autowired
    private PresentRepository presentRepository;

    public Absence enregistrerAbsence(String etudiantId, String emailVigile) {
        Absence absence = new Absence();
        absence.setEtudiantId(etudiantId);
        absence.setDate(LocalDate.now());
        absence.setHeure(LocalTime.now());
        absence.setJustifiee(false); // Par défaut non justifiée, tu peux ajouter un paramètre si besoin
        absence.setRetard(false);    // Par défaut pas de retard, à adapter selon besoin
        absence.setCreePar(emailVigile);

        return absenceRepository.save(absence);
    }

    public Present enregistrerPresent(String etudiantId, String emailVigile) {
        Present present = new Present();
        present.setEtudiantId(etudiantId);
        present.setDate(LocalDate.now());
        present.setHeure(LocalTime.now());
        present.setCreePar(emailVigile);

        return presentRepository.save(present);
    }
}
