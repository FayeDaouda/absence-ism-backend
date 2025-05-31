package com.ism.absences.service;

import com.ism.absences.entity.Pointage;
import com.ism.absences.repository.PointageRepository;
import org.springframework.stereotype.Service;

@Service
public class VigileService {

    private final PointageRepository pointageRepository;

    public VigileService(PointageRepository pointageRepository) {
        this.pointageRepository = pointageRepository;
    }

    public Pointage enregistrerPointage(Pointage pointage) {
        return pointageRepository.save(pointage);
    }
}
