package com.ism.absences.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Set;

@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username; // ou email
    private String password;
    private Set<String> roles; // ex: "ROLE_ADMIN", "ROLE_VIGILE", "ROLE_ETUDIANT"

    // getters et setters
}
