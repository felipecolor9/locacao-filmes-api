package com.lipsoft.locacaofilmesapi.controller;

import com.lipsoft.locacaofilmesapi.entities.Filme;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeNotFoundException;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import com.lipsoft.locacaofilmesapi.services.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {
    private FilmeService filmeService;

    @Autowired
    public FilmeController(FilmeService filmeService) {
        this.filmeService = filmeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse add(@RequestBody @Valid Filme filme) {
        return filmeService.add(filme);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Filme findById(@PathVariable Long id) throws FilmeNotFoundException {
        return filmeService.findByID(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Filme> findAll() {
        return filmeService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@RequestBody @Valid Filme filme, @PathVariable Long id) throws FilmeNotFoundException {
        return filmeService.update(filme,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse deleteById(@PathVariable Long id) throws FilmeNotFoundException {
        return filmeService.deleteById(id);
    }
}
