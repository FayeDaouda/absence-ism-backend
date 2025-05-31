package com.ism.absences.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@Document(collection = "absences")
public class Absence {

    @Id
    private String id;

    private String matricule;  // Identifiant étudiant

    private LocalDate dateAbsence;

    private String type; // Ex : "Absent", "Retard"

    private boolean justifie;

    private String motif; // Optionnel, justification texte

    // Constructeurs, getters/setters via Lombok @Data
}
