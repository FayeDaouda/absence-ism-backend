package com.ism.absences.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Document(collection = "presents")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Present {
    @Id
    private String id;
    private String etudiantId;
    private LocalDate date;
    private LocalTime heure;
    private String creePar; // email du vigile
}
