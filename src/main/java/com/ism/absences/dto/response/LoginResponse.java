package com.ism.absences.dto.response;

import com.ism.absences.enums.Role;

public class LoginResponse {
    private String email;
    private Role role;
    private String motDePasse;  // Si tu veux l’ajouter
    private String token;

    // Constructeur 4 paramètres
    public LoginResponse(String email, Role role, String motDePasse, String token) {
        this.email = email;
        this.role = role;
        this.motDePasse = motDePasse;
        this.token = token;
    }

    // Constructeur 3 paramètres
    public LoginResponse(String email, Role role, String token) {
        this(email, role, null, token);
    }

    // **Nouveau constructeur 2 paramètres**
    public LoginResponse(String email, Role role) {
        this(email, role, null, null);
    }

    // Getters et setters...



    // Getters et Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
