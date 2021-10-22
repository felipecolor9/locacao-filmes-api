package com.lipsoft.locacaofilmesapi.controller;

import com.lipsoft.locacaofilmesapi.entities.Locacao;
import com.lipsoft.locacaofilmesapi.exceptions.ClienteNotFoundException;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeAlreadyRentedException;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeNotFoundException;
import com.lipsoft.locacaofilmesapi.exceptions.LocacaoNotFoundException;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import com.lipsoft.locacaofilmesapi.services.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/locacoes")
public class LocacaoController {
    private LocacaoService locacaoService;

    @Autowired
    public LocacaoController(LocacaoService locacaoService) {
        this.locacaoService = locacaoService;
    }

    @PostMapping("/filme/{idFilme}/cliente/{idCliente}")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse rent(@RequestBody @Valid Locacao locacao, @PathVariable Long idFilme, @PathVariable Long idCliente) throws FilmeAlreadyRentedException, FilmeNotFoundException, ClienteNotFoundException {
        return locacaoService.add(locacao, idFilme, idCliente);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Locacao findByID(@PathVariable Long id) throws LocacaoNotFoundException { return locacaoService.findByID(id); }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Locacao> findAll() { return locacaoService.findAll(); }

}
