package com.ism.absences.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ism.absences.entity.Cours;
import com.ism.absences.service.CoursService;

@RestController
@RequestMapping("/api/cours")
@CrossOrigin(origins = "*")
public class CoursController {

    private final CoursService coursService;

    public CoursController(CoursService coursService) {
        this.coursService = coursService;
    }

    @GetMapping
    public List<Cours> getAllCours() {
        return coursService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cours> getCoursById(@PathVariable String id) {
        Optional<Cours> cours = coursService.findById(id);
        return cours.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/classe/{classeId}")
    public List<Cours> getCoursByClasse(@PathVariable String classeId) {
        return coursService.findByClasseId(classeId);
    }

    @GetMapping("/professeur/{nomProf}")
    public List<Cours> getCoursByProfesseur(@PathVariable String nomProf) {
        return coursService.findByProfesseur(nomProf);
    }

    @PostMapping
    public Cours createCours(@RequestBody Cours cours) {
        return coursService.save(cours);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cours> updateCours(@PathVariable String id, @RequestBody Cours coursDetails) {
        Optional<Cours> coursOpt = coursService.findById(id);
        if (coursOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Cours cours = coursOpt.get();
        cours.setNom(coursDetails.getNom());
        cours.setDescription(coursDetails.getDescription());
        cours.setClasseId(coursDetails.getClasseId());
        cours.setProfesseur(coursDetails.getProfesseur());

        return ResponseEntity.ok(coursService.save(cours));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCours(@PathVariable String id) {
        coursService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/semaine/{classeId}")
     public List<Cours> getCoursDeLaSemaine(@PathVariable String classeId) {
    return coursService.findCoursDeLaSemaine(classeId);
}
}
