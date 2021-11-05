package com.lipsoft.locacaofilmesapi.services;

import com.lipsoft.locacaofilmesapi.entities.Locacao;
import com.lipsoft.locacaofilmesapi.exceptions.*;
import com.lipsoft.locacaofilmesapi.repository.ClienteRepository;
import com.lipsoft.locacaofilmesapi.repository.FilmeRepository;
import com.lipsoft.locacaofilmesapi.repository.LocacaoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LocacaoService {

    private LocacaoRepository locacaoRepository;
    private ClienteRepository clienteRepository;
    private FilmeRepository filmeRepository;

    public LocacaoService(LocacaoRepository locacaoRepository, ClienteRepository clienteRepository, FilmeRepository filmeRepository) {
        this.locacaoRepository = locacaoRepository;
        this.clienteRepository = clienteRepository;
        this.filmeRepository = filmeRepository;
    }

    public Locacao add(Locacao locacao, long filmeId, long clientId) throws ClienteNotFoundException, FilmeNotFoundException, FilmeAlreadyRentedException, InvalidRentDataException {
        locacao.setDataInicioLocacao(LocalDateTime.now());

        if (locacao.getDataInicioLocacao().isBefore(locacao.getDataFimLocacao()) ) {
            if (verifyIfMovieIsAvailable(filmeId)) {
                locacao.setFilme(filmeRepository.getById(filmeId));
                locacao.setCliente(clienteRepository.getById(clientId));
                return locacaoRepository.save(locacao);

            } else throw new FilmeAlreadyRentedException(filmeId);
        } else throw new InvalidRentDataException(locacao.getDataInicioLocacao());
    }

    private boolean verifyIfMovieIsAvailable(long filmeId) throws FilmeAlreadyRentedException {
        if (findAll().stream().anyMatch
                (locacao -> locacao.getFilme().getId() == filmeId && locacao.getDataFimLocacao().isAfter(LocalDateTime.now()))) {
            throw new FilmeAlreadyRentedException(filmeId);
        }
        return true;
    }

    public List<Locacao> findAll() {
        return locacaoRepository.findAll();
    }

    public Locacao findByID(Long id) throws LocacaoNotFoundException {
        return locacaoRepository.findById(id).orElseThrow(() -> new LocacaoNotFoundException(id));
    }
}
