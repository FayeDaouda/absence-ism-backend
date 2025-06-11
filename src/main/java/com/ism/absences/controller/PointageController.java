package com.ism.absences.controller;

import com.ism.absences.dto.request.PointageRequestDTO;
import com.ism.absences.service.PointageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


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
    @GetMapping("/check")
public ResponseEntity<Map<String, Boolean>> checkPointage(
        @RequestParam String matricule,
        @RequestParam Long coursId) {

    boolean exists = pointageService.existsByMatriculeAndCoursIdForToday(matricule, coursId);
    Map<String, Boolean> response = Map.of("pointageExists", exists);

    return ResponseEntity.ok(response);
}

}
