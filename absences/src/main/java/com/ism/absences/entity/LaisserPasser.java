package com.ism.absences.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "laisser_passer")
public class LaisserPasser {

    @Id
    private String id;

    private String etudiantId; // référence Utilisateur (rôle ETUDIANT)
    private LocalDate dateEmission;
    private LocalDate dateExpiration;
    private String raison;

    // Constructeurs, getters, setters

    public LaisserPasser() {}

    public LaisserPasser(String id, String etudiantId, LocalDate dateEmission, LocalDate dateExpiration, String raison) {
        this.id = id;
        this.etudiantId = etudiantId;
        this.dateEmission = dateEmission;
        this.dateExpiration = dateExpiration;
        this.raison = raison;
    }

    // Getters & setters ...

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEtudiantId() { return etudiantId; }
    public void setEtudiantId(String etudiantId) { this.etudiantId = etudiantId; }
    public LocalDate getDateEmission() { return dateEmission; }
    public void setDateEmission(LocalDate dateEmission) { this.dateEmission = dateEmission; }
    public LocalDate getDateExpiration() { return dateExpiration; }
    public void setDateExpiration(LocalDate dateExpiration) { this.dateExpiration = dateExpiration; }
    public String getRaison() { return raison; }
    public void setRaison(String raison) { this.raison = raison; }
}
