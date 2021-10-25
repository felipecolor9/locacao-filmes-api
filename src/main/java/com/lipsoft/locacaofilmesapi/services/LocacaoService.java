package com.lipsoft.locacaofilmesapi.services;

import com.lipsoft.locacaofilmesapi.entities.Locacao;
import com.lipsoft.locacaofilmesapi.exceptions.*;
import com.lipsoft.locacaofilmesapi.repository.ClienteRepository;
import com.lipsoft.locacaofilmesapi.repository.FilmeRepository;
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
    private ClienteRepository clienteRepository;
    private FilmeService filmeService;
    private ClienteService clienteService;
    private FilmeRepository filmeRepository;
    private MessageResponse messageResponse;

    @Autowired
    public LocacaoService(LocacaoRepository locacaoRepository, ClienteRepository clienteRepository, FilmeService filmeService, ClienteService clienteService, FilmeRepository filmeRepository, MessageResponse messageResponse) {
        this.locacaoRepository = locacaoRepository;
        this.clienteRepository = clienteRepository;
        this.filmeService = filmeService;
        this.clienteService = clienteService;
        this.filmeRepository = filmeRepository;
        this.messageResponse = messageResponse;
    }

    public MessageResponse add(Locacao locacao, long filmeId, long clientId) throws ClienteNotFoundException, FilmeNotFoundException {
        locacao.setDataInicioLocacao(LocalDateTime.now());
        if (locacao.getDataInicioLocacao().isBefore(locacao.getDataFimLocacao()) ) {
            if (!(verifyIfMovieIsAlreadyRentedByClient(filmeId, clientId, locacao))) {
                clienteService.findByID(clientId);
                filmeService.findByID(filmeId);
                locacao.setFilme(filmeRepository.getById(filmeId));
                locacao.setCliente(clienteRepository.getById(clientId));

                locacaoRepository.save(locacao);
                return messageResponse.createMessageResponse("Alugando filme com ID= ", filmeId);
            } return messageResponse.createMessageResponse("O filme já está alugado em sua conta! ID= ", filmeId);
        } return messageResponse.createMessageResponse("Data inválida para alugar filme com ID= ", filmeId);
    }

    private boolean verifyIfMovieIsAlreadyRentedByClient(long filmeId ,long clienteId, Locacao locacaoPending) {
        var locationsByClient = findAll()
                .stream()
                .filter(locacao -> locacao.getFilme().getId() == filmeId && locacao.getCliente().getId() == clienteId)
                .collect(Collectors.toList());

        for (Locacao loc: locationsByClient) {
            if (loc.getDataFimLocacao().isAfter(locacaoPending.getDataInicioLocacao())) return true;
        }
        return false;
    }

    public List<Locacao> findAll() {
        return locacaoRepository.findAll();
    }

    public Locacao findByID(Long id) throws LocacaoNotFoundException {
        return locacaoRepository.findById(id).orElseThrow(() -> new LocacaoNotFoundException(id));
    }
}
