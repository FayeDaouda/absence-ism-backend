package com.ism.absences.service;

import com.ism.absences.entity.Absence;
import com.ism.absences.repository.AbsenceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbsenceService {

    private final AbsenceRepository absenceRepository;

    public AbsenceService(AbsenceRepository absenceRepository) {
        this.absenceRepository = absenceRepository;
    }

    public List<Absence> getAbsencesByMatricule(String matricule) {
        return absenceRepository.findByMatricule(matricule);
    }
}
