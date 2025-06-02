package com.ism.absences.mapper;

import com.ism.absences.dto.request.CoursRequestDTO;
import com.ism.absences.dto.response.CoursResponseDTO;
import com.ism.absences.entity.Cours;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CoursMapper {

    CoursResponseDTO toResponseDTO(Cours cours);

    Cours toEntity(CoursRequestDTO dto);

    void updateEntityFromDTO(CoursRequestDTO dto, @MappingTarget Cours entity);
}
