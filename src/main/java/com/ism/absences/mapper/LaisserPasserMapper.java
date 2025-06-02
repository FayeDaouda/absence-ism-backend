package com.ism.absences.mapper;

import com.ism.absences.dto.request.LaisserPasserRequestDTO;
import com.ism.absences.dto.response.LaisserPasserResponseDTO;
import com.ism.absences.entity.LaisserPasser;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface LaisserPasserMapper {

    LaisserPasserResponseDTO toResponseDTO(LaisserPasser entity);

    LaisserPasser toEntity(LaisserPasserRequestDTO dto);

    void updateEntityFromDTO(LaisserPasserRequestDTO dto, @MappingTarget LaisserPasser entity);
}
