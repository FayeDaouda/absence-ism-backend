package com.ism.absences.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "etudiants")
@Data
public class Etudiant {
    @Id
    private String id;
    private String prenom;
    private String nom;
    private String email;
    private String classe;
}
