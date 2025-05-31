package com.ism.absences.service;

import com.ism.absences.entity.Pointage;
import com.ism.absences.repository.PointageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PointageService {

    @Autowired
    private PointageRepository pointageRepository;

    public Pointage enregistrerPointage(Pointage pointage) {
        return pointageRepository.save(pointage);
    }
}

