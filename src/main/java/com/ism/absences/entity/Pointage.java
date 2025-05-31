package com.ism.absences.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "pointages")
public class Pointage {

    @Id
    private String id;

    private String matriculeEtudiant;
    private String emailVigile;
    private LocalDateTime dateHeure;

    public Pointage() {
        this.dateHeure = LocalDateTime.now();
    }

    public Pointage(String matriculeEtudiant, String emailVigile) {
        this.matriculeEtudiant = matriculeEtudiant;
        this.emailVigile = emailVigile;
        this.dateHeure = LocalDateTime.now();
    }

    // Getters et Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMatriculeEtudiant() { return matriculeEtudiant; }
    public void setMatriculeEtudiant(String matriculeEtudiant) { this.matriculeEtudiant = matriculeEtudiant; }

    public String getEmailVigile() { return emailVigile; }
    public void setEmailVigile(String emailVigile) { this.emailVigile = emailVigile; }

    public LocalDateTime getDateHeure() { return dateHeure; }
    public void setDateHeure(LocalDateTime dateHeure) { this.dateHeure = dateHeure; }
}
