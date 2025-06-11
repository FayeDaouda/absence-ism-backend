package com.ism.absences.dto.request;

public class EtatRequest {
    private String etat; // "A_JOUR" ou "PAS_A_JOUR"

    public EtatRequest() {}

    public EtatRequest(String etat) {
        this.etat = etat;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}