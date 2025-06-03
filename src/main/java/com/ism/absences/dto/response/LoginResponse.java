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

    // Getters & setters
}
