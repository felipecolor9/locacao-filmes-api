package com.lipsoft.locacaofilmesapi.services;

import com.lipsoft.locacaofilmesapi.entities.Locacao;
import com.lipsoft.locacaofilmesapi.exceptions.ClienteNotFoundException;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeAlreadyRentedException;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeNotFoundException;
import com.lipsoft.locacaofilmesapi.exceptions.LocacaoNotFoundException;
import com.lipsoft.locacaofilmesapi.repository.FilmeRepository;
import com.lipsoft.locacaofilmesapi.repository.LocacaoRepository;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class LocacaoService {
    private LocacaoRepository locacaoRepository;
    private FilmeRepository filmeRepository;
    private FilmeService filmeService;
    private ClienteService clienteService;

    @Autowired
    public LocacaoService(LocacaoRepository locacaoRepository, FilmeRepository filmeRepository, FilmeService filmeService, ClienteService clienteService) {
        this.locacaoRepository = locacaoRepository;
        this.filmeRepository = filmeRepository;
        this.filmeService = filmeService;
        this.clienteService = clienteService;
    }

    public void add(Locacao locacao, long filmeId, long clientId) throws FilmeNotFoundException, FilmeAlreadyRentedException, ClienteNotFoundException {
        if (verifyRentDisponibility(filmeId)) {
            locacao.setDataInicioLocacao(LocalDateTime.now());
            locacao.setCliente(clienteService.findByID(clientId));
            locacaoRepository.save(locacao);
            createMessageResponse("Alugando filme com ID= ", filmeId);
        }
    }

    //Terminar sexta.
    private boolean verifyRentDisponibility(long filmeId) throws FilmeAlreadyRentedException {

        var rentListWithMovie = findAll().stream()
                .filter(loc -> (loc.getFilme().getId() == filmeId))
                .collect(Collectors.toList());

        for(Locacao locFor: rentListWithMovie) {
            if (LocalDateTime.now().isBefore(locFor.getDataFimLocacao())) {
                throw new FilmeAlreadyRentedException(filmeId, locFor.getFilme().getNomeDoFilme(), locFor.getDataFimLocacao());
            }
        }
        return true;
    }

    public List<Locacao> findAll() {
        return locacaoRepository.findAll();
    }

    public Locacao findByID(Long id) throws LocacaoNotFoundException {
        return locacaoRepository.findById(id).orElseThrow(() -> new LocacaoNotFoundException(id));
    }

    public MessageResponse deleteById(Long id) throws LocacaoNotFoundException {
        locacaoRepository.findById(id).orElseThrow(() -> new LocacaoNotFoundException(id));
        locacaoRepository.deleteById(id);
        return createMessageResponse("Deletado o locacao com id= ",id);
    }

    public MessageResponse createMessageResponse(String msg, Long id) {
        return new MessageResponse.MessageResponseBuilder()
                .addMessage(msg)
                .addIdObj(id)
                .build();
    }
}
