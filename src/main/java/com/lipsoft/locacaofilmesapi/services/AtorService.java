package com.lipsoft.locacaofilmesapi.services;

import com.lipsoft.locacaofilmesapi.dto.AtorDTO;
import com.lipsoft.locacaofilmesapi.exceptions.AtorNotFoundException;
import com.lipsoft.locacaofilmesapi.mapper.AtorMapper;
import com.lipsoft.locacaofilmesapi.repository.AtorRepository;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtorService implements DbBasicsService<AtorDTO> {
    private AtorRepository atorRepository;
    private MessageResponse messageResponse;
    private final AtorMapper atorMapper = AtorMapper.INSTANCE;

    @Autowired
    public AtorService(AtorRepository atorRepository, MessageResponse messageResponse) {
        this.atorRepository = atorRepository;
        this.messageResponse = messageResponse;
    }

    @Override
    public MessageResponse add(AtorDTO atorDTO) {
        var ator = atorRepository.save(atorMapper.toModel(atorDTO));
        return messageResponse.createMessageResponse("Criando o ator com id= ", ator.getId());
    }

    @Override
    public List<AtorDTO> findAll() {
        return atorRepository.findAll()
                .stream().map(atorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AtorDTO findByID(Long id) throws AtorNotFoundException {
        return atorRepository.findById(id).map(atorMapper::toDTO).orElseThrow(() -> new AtorNotFoundException(id));
    }

    @Override
    public MessageResponse update(AtorDTO atorDTO, Long id) throws AtorNotFoundException {
        var ator = atorRepository.findById(id).orElseThrow(() -> new AtorNotFoundException(id));

        ator.setNome(atorDTO.getNome());
        ator.setIdade(atorDTO.getIdade());
        ator.setNomeDoPersonagem(atorDTO.getNomeDoPersonagem());

        atorRepository.save(ator);
        return messageResponse.createMessageResponse("Atualizando o ator com id= ", id);
    }

    @Override
    public MessageResponse deleteById(Long id) throws AtorNotFoundException {
        atorRepository.findById(id).orElseThrow(() -> new AtorNotFoundException(id));
        atorRepository.deleteById(id);
        return messageResponse.createMessageResponse("Deletando o ator com id= ", id);
    }
}