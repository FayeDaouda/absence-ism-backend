package com.ism.absences.controller;

import com.ism.absences.entity.LaisserPasser;
import com.ism.absences.service.LaisserPasserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laisser-passers")
@CrossOrigin(origins = "*")
public class LaisserPasserController {

    private final LaisserPasserService service;

    public LaisserPasserController(LaisserPasserService service) {
        this.service = service;
    }

    @GetMapping
    public List<LaisserPasser> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaisserPasser> getById(@PathVariable String id) {
        Optional<LaisserPasser> laisserPasser = service.findById(id);
        return laisserPasser.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/etudiant/{etudiantId}")
    public List<LaisserPasser> getByEtudiant(@PathVariable String etudiantId) {
        return service.findByEtudiantId(etudiantId);
    }

    @PostMapping
    public LaisserPasser create(@RequestBody LaisserPasser laisserPasser) {
        return service.save(laisserPasser);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LaisserPasser> update(@PathVariable String id, @RequestBody LaisserPasser updateData) {
        Optional<LaisserPasser> optional = service.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        LaisserPasser laisserPasser = optional.get();
        laisserPasser.setEtudiantId(updateData.getEtudiantId());
        laisserPasser.setDateEmission(updateData.getDateEmission());
        laisserPasser.setDateExpiration(updateData.getDateExpiration());
        laisserPasser.setRaison(updateData.getRaison());

        return ResponseEntity.ok(service.save(laisserPasser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
