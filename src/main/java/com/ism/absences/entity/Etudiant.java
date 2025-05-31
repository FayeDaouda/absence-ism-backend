package com.ism.absences.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "etudiants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {
    @Id
    private String id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private String classe;
    private String matricule;

    private String photoUrl;
    private String annee;
    private String etatScolarite;

    private String userId; // Lien avec le document User
}
