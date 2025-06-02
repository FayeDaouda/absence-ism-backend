package com.ism.absences.controller;

import com.ism.absences.entity.Classe;
import com.ism.absences.service.ClasseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/classes")
@CrossOrigin(origins = "*")
public class ClasseController {

    private final ClasseService classeService;

    public ClasseController(ClasseService classeService) {
        this.classeService = classeService;
    }

    @GetMapping
    public List<Classe> getAllClasses() {
        return classeService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Classe> getClasseById(@PathVariable String id) {
        Optional<Classe> classe = classeService.findById(id);
        return classe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/nom/{nom}")
    public Classe getClasseByNom(@PathVariable String nom) {
        return classeService.findByNom(nom);
    }

    @PostMapping
    public Classe createClasse(@RequestBody Classe classe) {
        return classeService.save(classe);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Classe> updateClasse(@PathVariable String id, @RequestBody Classe classeDetails) {
        Optional<Classe> classeOpt = classeService.findById(id);
        if (classeOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Classe classe = classeOpt.get();
        classe.setNom(classeDetails.getNom());
        classe.setDescription(classeDetails.getDescription());

        Classe updated = classeService.save(classe);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClasse(@PathVariable String id) {
        classeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
