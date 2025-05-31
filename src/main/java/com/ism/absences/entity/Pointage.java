package com.ism.absences.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "pointages")
public class Pointage {
    @Id
    private String id;
    private String matriculeEtudiant;
    private String emailVigile;
    private String etat;  // présent ou absent
    private LocalDateTime dateHeure = LocalDateTime.now();

    // Getters et setters
}
