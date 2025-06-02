package com.ism.absences.mapper;

import com.ism.absences.dto.request.JustificationRequestDTO;
import com.ism.absences.dto.response.JustificationResponseDTO;
import com.ism.absences.entity.Justification;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface JustificationMapper {

    JustificationResponseDTO toResponseDTO(Justification justification);

    Justification toEntity(JustificationRequestDTO dto);

    void updateEntityFromDTO(JustificationRequestDTO dto, @MappingTarget Justification entity);
}
