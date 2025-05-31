package com.ism.absences.dto;


public class LoginResponse {
    private String id;
    private String email;
    private String role;
    private String matricule;

    public LoginResponse(String id, String email, String role, String matricule) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.matricule = matricule;
    }

    // Getters uniquement (ou avec setters si tu veux)
    public String getId() { return id; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public String getMatricule() { return matricule; }
}
