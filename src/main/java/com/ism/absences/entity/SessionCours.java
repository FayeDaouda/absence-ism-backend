package com.ism.absences.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "session_cours")
public class SessionCours {

    @Id
    private String id;

    private String classeId;  // référence à une classe (string ou ObjectId selon ta modélisation)
    private String nomCours;
    private LocalDate dateCours;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String professeur;

    public SessionCours() {}

    public SessionCours(String id, String classeId, String nomCours, LocalDate dateCours, LocalTime heureDebut, LocalTime heureFin, String professeur) {
        this.id = id;
        this.classeId = classeId;
        this.nomCours = nomCours;
        this.dateCours = dateCours;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.professeur = professeur;
    }

    // Getters & Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClasseId() {
        return classeId;
    }

    public void setClasseId(String classeId) {
        this.classeId = classeId;
    }

    public String getNomCours() {
        return nomCours;
    }

    public void setNomCours(String nomCours) {
        this.nomCours = nomCours;
    }

    public LocalDate getDateCours() {
        return dateCours;
    }

    public void setDateCours(LocalDate dateCours) {
        this.dateCours = dateCours;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public String getProfesseur() {
        return professeur;
    }

    public void setProfesseur(String professeur) {
        this.professeur = professeur;
    }
}
