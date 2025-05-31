package com.ism.absences.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {

    @Id
    private String id;

    private String email;

    private String password;

    private String role;

    private String matricule; // ✅ Champ ajouté pour les étudiants
}
