package com.ism.absences.service;

import com.ism.absences.dto.PointageDTO;
import com.ism.absences.entity.Pointage;
import com.ism.absences.repository.PointageRepository;
import org.springframework.stereotype.Service;

@Service
public class PointageService {

    private final PointageRepository pointageRepository;

    public PointageService(PointageRepository pointageRepository) {
        this.pointageRepository = pointageRepository;
    }

    public Pointage enregistrerPointage(PointageDTO dto) {
        Pointage pointage = new Pointage(dto.getMatricule(), dto.getEmailVigile());
        return pointageRepository.save(pointage);
    }
}
