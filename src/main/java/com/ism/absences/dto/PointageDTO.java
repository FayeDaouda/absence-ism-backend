package com.ism.absences.dto;

public class PointageDTO {
    private String matricule;
    private String emailVigile;

    public PointageDTO() {}

    public PointageDTO(String matricule, String emailVigile) {
        this.matricule = matricule;
        this.emailVigile = emailVigile;
    }

    // Getters et Setters
    public String getMatricule() { return matricule; }
    public void setMatricule(String matricule) { this.matricule = matricule; }

    public String getEmailVigile() { return emailVigile; }
    public void setEmailVigile(String emailVigile) { this.emailVigile = emailVigile; }
}
