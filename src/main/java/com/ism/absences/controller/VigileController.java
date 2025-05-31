package com.ism.absences.controller;

import com.ism.absences.dto.PointageDTO;
import com.ism.absences.entity.Pointage;
import com.ism.absences.service.PointageService;

import java.time.LocalDateTime;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pointage")
public class VigileController {

    private final PointageService pointageService;

    public VigileController(PointageService pointageService) {
        this.pointageService = pointageService;
    }

    @PostMapping("/pointages")
    public ResponseEntity<Pointage> enregistrerPointage(@RequestBody PointageDTO pointageDTO) {
        // Conversion DTO -> Entity
        Pointage pointage = new Pointage();
        pointage.setMatriculeEtudiant(pointageDTO.getMatriculeEtudiant());
        pointage.setEmailVigile(pointageDTO.getEmailVigile());
        pointage.setEtat(pointageDTO.getEtat());
        pointage.setDateHeure(LocalDateTime.now());

        Pointage savedPointage = pointageService.enregistrerPointage(pointage);
        return ResponseEntity.ok(savedPointage);
    }
}
