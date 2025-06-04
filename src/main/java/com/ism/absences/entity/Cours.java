package com.ism.absences.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Document(collection = "cours")
public class Cours {

    @Id
    private String id;

    private String nom;
    private String description; // optionnel
    private String classeId; // référence à Classe
    private String professeur; // nom du prof ou id (si géré)
    private LocalDate date;

    private LocalDateTime dateHeureDebut;
    private LocalDateTime dateHeureFin;

    public Cours() {}

    public Cours(String id, String nom, String description, String classeId, String professeur,
                 LocalDateTime dateHeureDebut, LocalDateTime dateHeureFin) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.classeId = classeId;
        this.professeur = professeur;
        this.dateHeureDebut = dateHeureDebut;
        this.dateHeureFin = dateHeureFin;
    }

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

    public LocalDateTime getDateHeureDebut() { return dateHeureDebut; }
    public void setDateHeureDebut(LocalDateTime dateHeureDebut) { this.dateHeureDebut = dateHeureDebut; }

    public LocalDateTime getDateHeureFin() { return dateHeureFin; }
    public void setDateHeureFin(LocalDateTime dateHeureFin) { this.dateHeureFin = dateHeureFin; }
}
