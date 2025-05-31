package com.ism.absences.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "pointages")
public class Pointage {

    @Id
    private String id;

    private String etudiantId;

    private Date date;

    private Date heure;

    private String etat; // "present", "absent", "retard"

    private String creePar;

    private Boolean justifiee; // null si pas applicable (pour présence)

}
