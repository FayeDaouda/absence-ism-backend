package com.ism.absences.dto.response;

import java.time.LocalDate;

public class LaisserPasserResponseDTO {

    private String id;
    private String etudiantId;
    private LocalDate dateEmission;
    private LocalDate dateExpiration;
    private String raison;

    public LaisserPasserResponseDTO() {}

    public LaisserPasserResponseDTO(String id, String etudiantId, LocalDate dateEmission, LocalDate dateExpiration, String raison) {
        this.id = id;
        this.etudiantId = etudiantId;
        this.dateEmission = dateEmission;
        this.dateExpiration = dateExpiration;
        this.raison = raison;
    }

    // Getters & Setters

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
