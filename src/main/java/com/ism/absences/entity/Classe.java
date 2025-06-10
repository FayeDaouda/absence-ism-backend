package com.ism.absences.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "classes")
public class Classe {

    @Id
    private String id;

    private String nom; // ex : "L3 Informatique"
    private String description; // optionnel

    // Constructeurs, getters, setters

    public Classe() {}

    public Classe(String id, String nom, String description) {
        this.id = id;
        this.nom = nom;
        this.description = description;
    }

    // Getters & setters...

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
