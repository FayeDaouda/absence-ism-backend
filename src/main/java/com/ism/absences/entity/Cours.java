package com.ism.absences.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cours")
public class Cours {

    @Id
    private String id;

    private String nom;
    private String description; // optionnel
    private String classeId; // référence à Classe
    private String professeur; // nom du prof ou id (si géré)

    // Constructeurs, getters, setters

    public Cours() {}

    public Cours(String id, String nom, String description, String classeId, String professeur) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.classeId = classeId;
        this.professeur = professeur;
    }

    // Getters & setters ...

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getClasseId() { return classeId; }
    public void setClasseId(String classeId) { this.classeId = classeId; }
    public String getProfesseur() { return professeur; }
    public void setProfesseur(String professeur) { this.professeur = professeur; }
}
