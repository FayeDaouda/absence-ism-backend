package com.ism.absences.controller;

import com.ism.absences.dto.request.EtatRequest;
import com.ism.absences.entity.Utilisateur;
import com.ism.absences.service.UtilisateurService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
@CrossOrigin(origins = "*")
public class UtilisateurController {

    private final UtilisateurService utilisateurService;

    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping
    public List<Utilisateur> getAllUtilisateurs() {
        return utilisateurService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable String id) {
        Optional<Utilisateur> utilisateur = utilisateurService.findById(id);
        return utilisateur.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/matricule/{matricule}")
    public ResponseEntity<Utilisateur> getUtilisateurByMatricule(@PathVariable String matricule) {
        Optional<Utilisateur> utilisateur = utilisateurService.findByMatricule(matricule);
        return utilisateur.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Utilisateur createUtilisateur(@RequestBody Utilisateur utilisateur) {
        return utilisateurService.save(utilisateur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable String id, @RequestBody Utilisateur utilisateurDetails) {
        Optional<Utilisateur> utilisateurOpt = utilisateurService.findById(id);
        if (utilisateurOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Utilisateur utilisateur = utilisateurOpt.get();

        utilisateur.setNom(utilisateurDetails.getNom());
        utilisateur.setPrenom(utilisateurDetails.getPrenom());
        utilisateur.setEmail(utilisateurDetails.getEmail());
        utilisateur.setMotDePasse(utilisateurDetails.getMotDePasse());
        utilisateur.setRole(utilisateurDetails.getRole());
        utilisateur.setPhoto(utilisateurDetails.getPhoto());
        utilisateur.setMatricule(utilisateurDetails.getMatricule());
        utilisateur.setClasseId(utilisateurDetails.getClasseId());

        Utilisateur updated = utilisateurService.save(utilisateur);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable String id) {
        utilisateurService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/etudiants")
public List<Utilisateur> getAllEtudiants() {
    return utilisateurService.findByRole("ETUDIANT");
}
@GetMapping("/role/{role}")
public ResponseEntity<List<Utilisateur>> getUtilisateursByRole(@PathVariable String role) {
    List<Utilisateur> utilisateurs = utilisateurService.findByRole(role);
    if (utilisateurs.isEmpty()) {
        return ResponseEntity.noContent().build(); // 204 si aucun résultat
    }
    return ResponseEntity.ok(utilisateurs); // 200 avec liste
}
@GetMapping("/classe/{id}")
public ResponseEntity<List<Utilisateur>> getUtilisateursByClasse(@PathVariable("id") String classeId) {
    List<Utilisateur> utilisateurs = utilisateurService.findByClasseId(classeId);
    if (utilisateurs.isEmpty()) {
        return ResponseEntity.noContent().build(); // 204 No Content
    }
    return ResponseEntity.ok(utilisateurs); // 200 OK
}

@PutMapping("/{id}/etat")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity<?> updateEtat(
        @PathVariable String id,
        @RequestBody EtatRequest request
) {
    try {
        Utilisateur updated = utilisateurService.mettreAJourEtat(id, request.getEtat());
        return ResponseEntity.ok(updated);
    } catch (RuntimeException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}


}
