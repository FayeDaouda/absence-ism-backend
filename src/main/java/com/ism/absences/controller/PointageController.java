package com.ism.absences.controller;

import com.ism.absences.dto.request.PointageRequestDTO;
import com.ism.absences.service.PointageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pointage")
public class PointageController {

    private final PointageService pointageService;

    public PointageController(PointageService pointageService) {
        this.pointageService = pointageService;
    }

    @PostMapping("/automatique")
    public ResponseEntity<?> pointerAutomatiquement(@RequestBody PointageRequestDTO request) {
        return pointageService.pointerAutomatiquement(request);
    }
}
