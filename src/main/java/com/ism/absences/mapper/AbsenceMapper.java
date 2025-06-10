// AbsenceMapper.java
package com.ism.absences.mapper;

import com.ism.absences.entity.Absence;
import com.ism.absences.dto.request.AbsenceRequestDTO;
import com.ism.absences.dto.response.AbsenceResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AbsenceMapper {

    Absence toEntity(AbsenceRequestDTO dto);

    AbsenceResponseDTO toResponseDTO(Absence entity);

    void updateEntityFromDto(AbsenceRequestDTO dto, @MappingTarget Absence entity);
}
