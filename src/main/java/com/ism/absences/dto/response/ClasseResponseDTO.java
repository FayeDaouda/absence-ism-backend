package com.ism.absences.dto.response;

public class ClasseResponseDTO {

    private String id;
    private String nom;
    private String description;

    public ClasseResponseDTO() {}

    public ClasseResponseDTO(String id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
