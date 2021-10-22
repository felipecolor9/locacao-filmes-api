package com.lipsoft.locacaofilmesapi.services;

import com.lipsoft.locacaofilmesapi.entities.Locacao;
import com.lipsoft.locacaofilmesapi.exceptions.ClienteNotFoundException;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeAlreadyRentedException;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeNotFoundException;
import com.lipsoft.locacaofilmesapi.exceptions.LocacaoNotFoundException;
import com.lipsoft.locacaofilmesapi.repository.LocacaoRepository;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocacaoService {
    private LocacaoRepository locacaoRepository;
    private ClienteService clienteService;
    private FilmeService filmeService;

    @Autowired
    public LocacaoService(LocacaoRepository locacaoRepository, ClienteService clienteService,  FilmeService filmeService) {
        this.locacaoRepository = locacaoRepository;
        this.clienteService = clienteService;
        this.filmeService = filmeService;
    }

    public MessageResponse add(Locacao locacao, long filmeId, long clientId) throws FilmeNotFoundException, FilmeAlreadyRentedException, ClienteNotFoundException {
        if (verifyRentDisponibility(filmeId)) {
            locacao.setDataInicioLocacao(LocalDateTime.now());
            locacao.setFilme(filmeService.findByID(filmeId));
            locacao.setCliente(clienteService.findByID(clientId));
            locacaoRepository.save(locacao);
            return createMessageResponse("Alugando filme com ID= ", filmeId);
        }
        return createMessageResponse("Não foi possível alugar filme com ID= ", filmeId);
    }

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

    public MessageResponse createMessageResponse(String msg, Long id) {
        return new MessageResponse.MessageResponseBuilder()
                .addMessage(msg)
                .addIdObj(id)
                .build();
    }
}
