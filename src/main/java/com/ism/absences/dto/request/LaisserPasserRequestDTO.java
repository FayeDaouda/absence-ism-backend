package com.ism.absences.dto.request;

import java.time.LocalDate;

public class LaisserPasserRequestDTO {

    private String etudiantId;
    private LocalDate dateEmission;
    private LocalDate dateExpiration;
    private String raison;

    public LaisserPasserRequestDTO() {}

    public LaisserPasserRequestDTO(String etudiantId, LocalDate dateEmission, LocalDate dateExpiration, String raison) {
        this.etudiantId = etudiantId;
        this.dateEmission = dateEmission;
        this.dateExpiration = dateExpiration;
        this.raison = raison;
    }

    // Getters & Setters

    public String getEtudiantId() { return etudiantId; }
    public void setEtudiantId(String etudiantId) { this.etudiantId = etudiantId; }

    public LocalDate getDateEmission() { return dateEmission; }
    public void setDateEmission(LocalDate dateEmission) { this.dateEmission = dateEmission; }

    public LocalDate getDateExpiration() { return dateExpiration; }
    public void setDateExpiration(LocalDate dateExpiration) { this.dateExpiration = dateExpiration; }

    public String getRaison() { return raison; }
    public void setRaison(String raison) { this.raison = raison; }
}
