package com.ism.absences.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.ism.absences.enums.Role;

@Document(collection = "utilisateurs")
public class Utilisateur {

    @Id
    private String id;

    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Role role; // Enum à définir
    private String photo; // URL ou base64
    private String matricule;
    private String classeId; // référence à Classe
    private String etat; // "A_JOUR" ou "PAS_A_JOUR"

    // Constructeurs
    public Utilisateur() {}

    public Utilisateur(String id, String nom, String prenom, String email, String motDePasse, Role role, String photo, String matricule, String classeId, String etat) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.motDePasse = motDePasse;
        this.role = role;
        this.photo = photo;
        this.matricule = matricule;
        this.classeId = classeId;
        this.etat = etat;
    }

    // Getters et Setters
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMatricule() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public String getClasseId() {
        return classeId;
    }

    public void setClasseId(String classeId) {
        this.classeId = classeId;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
}
