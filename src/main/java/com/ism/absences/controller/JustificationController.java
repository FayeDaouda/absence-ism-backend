package com.ism.absences.controller;

import com.ism.absences.entity.Justification;
import com.ism.absences.service.JustificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/justifications")
public class JustificationController {

    @Autowired
    private JustificationService justificationService;

    @GetMapping
    public List<Justification> getAll() {
        return justificationService.getAllJustifications(); // ✅ méthode correcte
    }

    @GetMapping("/etudiant/{id}")
    public List<Justification> getByEtudiant(@PathVariable String id) {
        return justificationService.getByEtudiantId(id); // ✅ méthode correcte
    }

    @GetMapping("/absence/{id}")
    public List<Justification> getByAbsence(@PathVariable String id) {
        return justificationService.getByAbsenceId(id); // ✅ méthode correcte
    }

    @GetMapping("/statut/{statut}")
    public List<Justification> getByStatut(@PathVariable String statut) {
        return justificationService.getByStatut(statut); // ✅ méthode correcte
    }

    @PostMapping
    public Justification create(@RequestBody Justification justification) {
        return justificationService.create(justification); // ✅ méthode correcte
    }

    @PutMapping("/{id}")
    public ResponseEntity<Justification> update(@PathVariable String id, @RequestBody Justification updated) {
        Optional<Justification> justification = justificationService.update(id, updated); // ✅ méthode correcte
        return justification.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        justificationService.delete(id); // ✅ méthode correcte
        return ResponseEntity.noContent().build();
    }
}
