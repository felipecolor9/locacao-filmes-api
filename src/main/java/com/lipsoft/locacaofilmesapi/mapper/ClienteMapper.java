package com.lipsoft.locacaofilmesapi.mapper;

import com.lipsoft.locacaofilmesapi.dto.ClienteDTO;
import com.lipsoft.locacaofilmesapi.entities.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    Cliente toModel(ClienteDTO clienteDTO);

    ClienteDTO toDTO(Cliente cliente);
}
