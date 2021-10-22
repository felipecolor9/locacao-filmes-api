package com.lipsoft.locacaofilmesapi.services;

import com.lipsoft.locacaofilmesapi.entities.Filme;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeNotFoundException;
import com.lipsoft.locacaofilmesapi.repository.FilmeRepository;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService implements DbBasicsService<Filme> {
    private FilmeRepository filmeRepository;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository) {
        this.filmeRepository = filmeRepository;
    }

    @Override
    public MessageResponse add(Filme filme) {
        filmeRepository.save(filme);
        return createMessageResponse("Criado o filme com id= ",filme.getId());
    }

    @Override
    public List<Filme> findAll() {
        return filmeRepository.findAll();
    }

    @Override
    public Filme findByID(Long id) throws FilmeNotFoundException {
        return filmeRepository.findById(id).orElseThrow(() -> new FilmeNotFoundException(id));
    }

    @Override
    public MessageResponse update(Filme filme, Long id) throws FilmeNotFoundException {
        var filmeToBeUpdated = filmeRepository.findById(id).orElseThrow(() -> new FilmeNotFoundException(id));

        filmeToBeUpdated.setNomeDoFilme(filme.getNomeDoFilme());
        filmeToBeUpdated.setAnoDeLancamento(filme.getAnoDeLancamento());
        filmeToBeUpdated.setNotaDosUsuarios(filme.getNotaDosUsuarios());
        filmeToBeUpdated.setNotaDaCritica(filme.getNotaDaCritica());

        filmeRepository.save(filmeToBeUpdated);
        return createMessageResponse("Atualizado o filme com o id= ", filme.getId());
    }

    @Override
    public MessageResponse deleteById(Long id) throws FilmeNotFoundException {
        var filmFounded = filmeRepository.findById(id).orElseThrow(() -> new FilmeNotFoundException(id));
        filmeRepository.deleteById(id);
        return createMessageResponse("Deletado o filme com id= ", filmFounded.getId());
    }

    public MessageResponse createMessageResponse(String msg, Long id) {
        return new MessageResponse.MessageResponseBuilder()
                .addMessage(msg)
                .addIdObj(id)
                .build();
    }
}
