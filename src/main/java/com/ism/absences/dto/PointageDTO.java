package com.ism.absences.dto;

public class PointageDTO {
    private String matriculeEtudiant;
    private String emailVigile;
    private String etat;

    public PointageDTO() {
    }

    public String getMatriculeEtudiant() {
        return matriculeEtudiant;
    }

    public void setMatriculeEtudiant(String matriculeEtudiant) {
        this.matriculeEtudiant = matriculeEtudiant;
    }

    public String getEmailVigile() {
        return emailVigile;
    }

    public void setEmailVigile(String emailVigile) {
        this.emailVigile = emailVigile;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
