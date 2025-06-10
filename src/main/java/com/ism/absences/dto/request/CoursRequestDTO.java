package com.ism.absences.dto.request;

public class CoursRequestDTO {

    private String nom;
    private String description;
    private String classeId;
    private String professeur;

    public CoursRequestDTO() {}

    public CoursRequestDTO(String nom, String description, String classeId, String professeur) {
        this.nom = nom;
        this.description = description;
        this.classeId = classeId;
        this.professeur = professeur;
    }

    // Getters & Setters

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getClasseId() { return classeId; }
    public void setClasseId(String classeId) { this.classeId = classeId; }

    public String getProfesseur() { return professeur; }
    public void setProfesseur(String professeur) { this.professeur = professeur; }
}
