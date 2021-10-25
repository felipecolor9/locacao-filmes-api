package com.lipsoft.locacaofilmesapi.services;

import com.lipsoft.locacaofilmesapi.entities.Ator;
import com.lipsoft.locacaofilmesapi.exceptions.AtorNotFoundException;
import com.lipsoft.locacaofilmesapi.repository.AtorRepository;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtorService implements DbBasicsService<Ator> {
    private AtorRepository atorRepository;
    private MessageResponse messageResponse;

    @Autowired
    public AtorService(AtorRepository atorRepository, MessageResponse messageResponse) {
        this.atorRepository = atorRepository;
        this.messageResponse = messageResponse;
    }

    @Override
    public MessageResponse add(Ator ator) {
        atorRepository.save(ator);
        return messageResponse.createMessageResponse("Criando o ator com id= ", ator.getId());
    }

    @Override
    public List<Ator> findAll() {
        return atorRepository.findAll();
    }

    @Override
    public Ator findByID(Long id) throws AtorNotFoundException {
        return atorRepository.findById(id).orElseThrow(() -> new AtorNotFoundException(id));
    }

    @Override
    public MessageResponse update(Ator ator, Long id) throws AtorNotFoundException {
        var actorToBeUpdated = atorRepository.findById(id).orElseThrow(() -> new AtorNotFoundException(id));

        actorToBeUpdated.setNome(ator.getNome());
        actorToBeUpdated.setIdade(ator.getIdade());
        actorToBeUpdated.setNomeDoPersonagem(ator.getNomeDoPersonagem());

        atorRepository.save(actorToBeUpdated);
        return messageResponse.createMessageResponse("Atualizando o ator com id= ", id);
    }

    @Override
    public MessageResponse deleteById(Long id) throws AtorNotFoundException {
        atorRepository.findById(id).orElseThrow(() -> new AtorNotFoundException(id));
        atorRepository.deleteById(id);
        return messageResponse.createMessageResponse("Deletando o ator com id= ", id);
    }
}