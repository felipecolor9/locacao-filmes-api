package com.lipsoft.locacaofilmesapi.mapper;

import com.lipsoft.locacaofilmesapi.dto.AtorDTO;
import com.lipsoft.locacaofilmesapi.entities.Ator;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AtorMapper {

    AtorMapper INSTANCE = Mappers.getMapper(AtorMapper.class);

    Ator toModel(AtorDTO atorDTO);

    AtorDTO toDTO(Ator ator);
}
