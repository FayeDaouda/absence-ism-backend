package com.ism.absences.dto.response;

import java.time.LocalDate;
import java.util.List;

public class JustificationResponseDTO {

    private String id;
    private LocalDate dateSoumission;
    private String motif;
    private List<String> fichiers;
    private String statut;
    private String absenceId;
    private String etudiantId;

    public JustificationResponseDTO() {}

    public JustificationResponseDTO(String id, LocalDate dateSoumission, String motif, List<String> fichiers, String statut, String absenceId, String etudiantId) {
        this.id = id;
        this.dateSoumission = dateSoumission;
        this.motif = motif;
        this.fichiers = fichiers;
        this.statut = statut;
        this.absenceId = absenceId;
        this.etudiantId = etudiantId;
    }

    // Getters & Setters

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

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
