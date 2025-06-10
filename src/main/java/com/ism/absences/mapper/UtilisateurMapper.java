package com.ism.absences.mapper;

import com.ism.absences.dto.request.UtilisateurRequestDto;
import com.ism.absences.dto.response.UtilisateurResponseDto;
import com.ism.absences.entity.Utilisateur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UtilisateurMapper {

    UtilisateurMapper INSTANCE = Mappers.getMapper(UtilisateurMapper.class);

    // Mapping pour créer une entité à partir d’un DTO de requête
    Utilisateur toEntity(UtilisateurRequestDto dto);

    // Mapping pour créer un DTO de réponse à partir d’une entité
    UtilisateurResponseDto toDto(Utilisateur utilisateur);
}
