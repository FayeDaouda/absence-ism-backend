package com.ism.absences.controller;

import com.ism.absences.entity.Justification;
import com.ism.absences.service.JustificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/justifications")
@CrossOrigin(origins = "*")
public class JustificationController {

    private final JustificationService justificationService;

    public JustificationController(JustificationService justificationService) {
        this.justificationService = justificationService;
    }

    @GetMapping
    public List<Justification> getAll() {
        return justificationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Justification> getById(@PathVariable String id) {
        Optional<Justification> justification = justificationService.findById(id);
        return justification.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/etudiant/{etudiantId}")
    public List<Justification> getByEtudiant(@PathVariable String etudiantId) {
        return justificationService.findByEtudiantId(etudiantId);
    }

    @GetMapping("/absence/{absenceId}")
    public List<Justification> getByAbsence(@PathVariable String absenceId) {
        return justificationService.findByAbsenceId(absenceId);
    }

    @GetMapping("/statut/{statut}")
    public List<Justification> getByStatut(@PathVariable String statut) {
        return justificationService.findByStatut(statut);
    }

    @PostMapping
    public Justification create(@RequestBody Justification justification) {
        return justificationService.save(justification);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Justification> update(@PathVariable String id, @RequestBody Justification updateData) {
        Optional<Justification> justificationOpt = justificationService.findById(id);
        if (justificationOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Justification justification = justificationOpt.get();
        justification.setDateSoumission(updateData.getDateSoumission());
        justification.setMotif(updateData.getMotif());
        justification.setFichiers(updateData.getFichiers());
        justification.setStatut(updateData.getStatut());
        justification.setAbsenceId(updateData.getAbsenceId());
        justification.setEtudiantId(updateData.getEtudiantId());

        return ResponseEntity.ok(justificationService.save(justification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        justificationService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
