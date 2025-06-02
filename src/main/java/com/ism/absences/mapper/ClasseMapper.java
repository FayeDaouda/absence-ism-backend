package com.ism.absences.mapper;

import com.ism.absences.dto.request.ClasseRequestDTO;
import com.ism.absences.dto.response.ClasseResponseDTO;
import com.ism.absences.entity.Classe;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClasseMapper {

    // Convertit l’entité en DTO réponse
    ClasseResponseDTO toResponseDTO(Classe classe);

    // Convertit le DTO requête en entité
    Classe toEntity(ClasseRequestDTO dto);

    // Met à jour une entité existante avec un DTO requête
    void updateEntityFromDTO(ClasseRequestDTO dto, @MappingTarget Classe entity);
}
