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

    
}
