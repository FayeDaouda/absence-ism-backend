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

    public List<Absence> findAll() {
        return absenceRepository.findAll();
    }

    public Optional<Absence> findById(String id) {
        return absenceRepository.findById(id);
    }

    public List<Absence> findByEtudiantId(String etudiantId) {
        return absenceRepository.findByEtudiantId(etudiantId);
    }

    public Absence save(Absence absence) {
        return absenceRepository.save(absence);
    }

    public void deleteById(String id) {
        absenceRepository.deleteById(id);
    }
    public List<Absence> getAbsencesByVigile(String vigileId) {
        return absenceRepository.findByVigileId(vigileId);
    }
    
}
