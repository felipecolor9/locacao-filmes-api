package com.lipsoft.locacaofilmesapi.services;

import com.lipsoft.locacaofilmesapi.dto.FilmeDTO;
import com.lipsoft.locacaofilmesapi.entities.Filme;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeNotFoundException;
import com.lipsoft.locacaofilmesapi.mapper.FilmeMapper;
import com.lipsoft.locacaofilmesapi.repository.FilmeRepository;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class FilmeService implements DbBasicsService<FilmeDTO> {

    private FilmeRepository filmeRepository;
    private final MessageResponse messageResponse;
    private final FilmeMapper filmeMapper = FilmeMapper.INSTANCE;

    @Override
    public MessageResponse add(FilmeDTO filmeDTO) {
        var filme = filmeRepository.save(filmeMapper.toModel(filmeDTO));
        filme.setAtores(new ArrayList<>());
        return messageResponse.createMessageResponse("Criado o filme com id= ", filme.getId());
    }

    @Override
    public List<FilmeDTO> findAll() {
        return filmeRepository.findAll().stream()
                .map(filmeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<Filme> findAllModel() {
        return filmeRepository.findAll();
    }

    @Override
    public FilmeDTO findByID(Long id) throws FilmeNotFoundException {
        return filmeRepository.findById(id).map(filmeMapper::toDTO).orElseThrow(() -> new FilmeNotFoundException(id));
    }

    @Override
    public MessageResponse update(FilmeDTO filmeDto, Long id) throws FilmeNotFoundException {
        var filme = filmeRepository.findById(id).orElseThrow(() -> new FilmeNotFoundException(id));
        filme.setNomeDoFilme(filmeDto.getNomeDoFilme());
        filme.setAnoDeLancamento(filmeDto.getAnoDeLancamento());
        filme.setNotaDosUsuarios(filmeDto.getNotaDosUsuarios());
        filme.setNotaDaCritica(filmeDto.getNotaDaCritica());
        filmeRepository.save(filme);
        return messageResponse.createMessageResponse("Atualizado o filme com o id= ", id);

    }

    @Override
    public MessageResponse deleteById(Long id) throws FilmeNotFoundException {
        filmeRepository.findById(id).orElseThrow(() -> new FilmeNotFoundException(id));
        filmeRepository.deleteById(id);
        return messageResponse.createMessageResponse("Deletado o filme com id= ", id);
    }
}
