package com.ism.absences.service;

import com.ism.absences.entity.Justification;
import com.ism.absences.repository.JustificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JustificationService {

    @Autowired
    private JustificationRepository justificationRepository;

    public List<Justification> getAllJustifications() {
        return justificationRepository.findAll();
    }

    public List<Justification> getByEtudiantId(String etudiantId) {
        return justificationRepository.findByEtudiantId(etudiantId);
    }

    public List<Justification> getByAbsenceId(String absenceId) {
        return justificationRepository.findByAbsenceId(absenceId);
    }

    public List<Justification> getByStatut(String statut) {
        return justificationRepository.findByStatut(statut);
    }

    public Justification create(Justification justification) {
        return justificationRepository.save(justification);
    }

    public Optional<Justification> update(String id, Justification updated) {
        return justificationRepository.findById(id).map(justification -> {
            justification.setStatut(updated.getStatut());
            justification.setMotif(updated.getMotif());
            justification.setFichiers(updated.getFichiers()); // ✅ ici la correction
            return justificationRepository.save(justification);
        });
    }

    public void delete(String id) {
        justificationRepository.deleteById(id);
    }

    public Optional<Justification> findById(String id) {
        return justificationRepository.findById(id);
    }
}
