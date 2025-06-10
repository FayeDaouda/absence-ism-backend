package com.ism.absences.dto.request;


public class PointageRequestDTO {
    private String matricule;
    private String vigileId;

    // getters et setters
    public String getMatricule() {
        return matricule;
    }
    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }
    public String getVigileId() {
        return vigileId;
    }
    public void setVigileId(String vigileId) {
        this.vigileId = vigileId;
    }
}
