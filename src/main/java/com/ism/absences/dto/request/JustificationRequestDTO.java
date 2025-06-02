package com.ism.absences.dto.request;

import java.time.LocalDate;
import java.util.List;

public class JustificationRequestDTO {

    private LocalDate dateSoumission;
    private String motif;
    private List<String> fichiers; // URLs ou base64
    private String statut; // "En attente", "Validée", "Rejetée"
    private String absenceId;
    private String etudiantId;

    public JustificationRequestDTO() {}

    public JustificationRequestDTO(LocalDate dateSoumission, String motif, List<String> fichiers, String statut, String absenceId, String etudiantId) {
        this.dateSoumission = dateSoumission;
        this.motif = motif;
        this.fichiers = fichiers;
        this.statut = statut;
        this.absenceId = absenceId;
        this.etudiantId = etudiantId;
    }

    // Getters & Setters

    public LocalDate getDateSoumission() { return dateSoumission; }
    public void setDateSoumission(LocalDate dateSoumission) { this.dateSoumission = dateSoumission; }

    public String getMotif() { return motif; }
    public void setMotif(String motif) { this.motif = motif; }

    public List<String> getFichiers() { return fichiers; }
    public void setFichiers(List<String> fichiers) { this.fichiers = fichiers; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public String getAbsenceId() { return absenceId; }
    public void setAbsenceId(String absenceId) { this.absenceId = absenceId; }

    public String getEtudiantId() { return etudiantId; }
    public void setEtudiantId(String etudiantId) { this.etudiantId = etudiantId; }
}
