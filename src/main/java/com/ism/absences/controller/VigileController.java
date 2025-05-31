package com.ism.absences.controller;

import com.ism.absences.dto.PointageDTO;
import com.ism.absences.entity.Pointage;
import com.ism.absences.service.PointageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pointage")
public class VigileController {

    private final PointageService pointageService;

    public VigileController(PointageService pointageService) {
        this.pointageService = pointageService;
    }

    @PostMapping
    public ResponseEntity<?> enregistrerPointage(@RequestBody PointageDTO dto) {
        try {
            Pointage p = pointageService.enregistrerPointage(dto);
            return ResponseEntity.ok(p);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l'enregistrement du pointage");
        }
    }
}
