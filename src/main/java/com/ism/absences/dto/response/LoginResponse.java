package com.ism.absences.dto.response;

import com.ism.absences.enums.Role;

public class LoginResponse {
    private String id;
    private String email;
    private Role role;
    private String token;

    public LoginResponse(String id, String email, Role role, String token) {
        this.id = id;
        this.email = email;
        this.role = role;
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
}
