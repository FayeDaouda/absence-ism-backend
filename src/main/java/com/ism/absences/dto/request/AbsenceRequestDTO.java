// AbsenceRequestDTO.java
package com.ism.absences.dto.request;

import java.time.LocalDate;
import java.time.LocalTime;

public class AbsenceRequestDTO {

    private LocalDate date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private String statut; // "Présent", "Absent", "Retard"
    private String etudiantId;
    private String vigileId;
    private String coursId;
    private String justificationId; // optionnel

    // Getters et setters

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
