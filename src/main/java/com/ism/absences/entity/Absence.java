package com.ism.absences.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "absences")
public class Absence {

    @Id
    private String id;

    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String statut; // "Présent", "Absent", "Retard"
    
    private String etudiantId; // référence à Utilisateur (rôle ETUDIANT)
    private String vigileId;   // référence à Utilisateur (rôle VIGILE)
    private String coursId;    // référence à Cours
    private String justificationId; // optionnel

    // Constructeurs, getters, setters

    public Absence() {}

    public Absence(String id, LocalDate date, LocalTime heureDebut, LocalTime heureFin, String statut,
                   String etudiantId, String vigileId, String coursId, String justificationId) {
        this.id = id;
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.statut = statut;
        this.etudiantId = etudiantId;
        this.vigileId = vigileId;
        this.coursId = coursId;
        this.justificationId = justificationId;
    }

    // Getters & Setters ...

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
    public LocalTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalTime heureDebut) { this.heureDebut = heureDebut; }
    public LocalTime getHeureFin() { return heureFin; }
    public void setHeureFin(LocalTime heureFin) { this.heureFin = heureFin; }
    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }
    public String getEtudiantId() { return etudiantId; }
    public void setEtudiantId(String etudiantId) { this.etudiantId = etudiantId; }
    public String getVigileId() { return vigileId; }
    public void setVigileId(String vigileId) { this.vigileId = vigileId; }
    public String getCoursId() { return coursId; }
    public void setCoursId(String coursId) { this.coursId = coursId; }
    public String getJustificationId() { return justificationId; }
    public void setJustificationId(String justificationId) { this.justificationId = justificationId; }
}