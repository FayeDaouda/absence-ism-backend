package com.ism.absences.dto.response;

import com.ism.absences.enums.Role;

public class LoginResponse {
    private String email;
    private Role role;
    private String token;

    public LoginResponse(String email, Role role, String token) {
        this.email = email;
        this.role = role;
        this.token = token;
    }

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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    // Getters & setters
}
