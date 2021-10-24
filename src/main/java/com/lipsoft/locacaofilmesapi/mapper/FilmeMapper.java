package com.lipsoft.locacaofilmesapi.mapper;

import com.lipsoft.locacaofilmesapi.dto.FilmeDTO;
import com.lipsoft.locacaofilmesapi.entities.Filme;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FilmeMapper {

    FilmeMapper INSTANCE = Mappers.getMapper(FilmeMapper.class);

    Filme toModel(FilmeDTO filmeDTO);

    FilmeDTO toDTO(Filme filme);
}
