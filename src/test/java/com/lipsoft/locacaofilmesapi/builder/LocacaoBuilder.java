package com.lipsoft.locacaofilmesapi.builder;

import com.lipsoft.locacaofilmesapi.dto.ClienteDTO;
import com.lipsoft.locacaofilmesapi.entities.Cliente;
import com.lipsoft.locacaofilmesapi.entities.Filme;
import com.lipsoft.locacaofilmesapi.mapper.ClienteMapper;
import com.lipsoft.locacaofilmesapi.mapper.FilmeMapper;
import lombok.Builder;

@Builder
public class LocacaoBuilder {
    private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;
    private final FilmeMapper filmeMapper = FilmeMapper.INSTANCE;

    @Builder.Default
    private Long id= 1L;

    @Builder.Default
    private Filme filme = filmeMapper.toModel(FilmeDTOBuilder.builder().build().toFilmeDTO());

    @Builder.Default
    private Cliente cliente = clienteMapper.toModel(ClienteDTOBuilder.builder().build().toClienteDTO());



}
