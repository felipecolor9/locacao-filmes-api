package com.lipsoft.locacaofilmesapi.controller;

import com.lipsoft.locacaofilmesapi.entities.Ator;
import com.lipsoft.locacaofilmesapi.exceptions.AtorNotFoundException;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import com.lipsoft.locacaofilmesapi.services.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/filme/ator")
public class AtorController {

    private AtorService atorService;

    @Autowired
    public AtorController() {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse add(@RequestBody @Valid Ator ator) {
        return atorService.add(ator);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Ator findById(@PathVariable Long id) throws AtorNotFoundException {
        return atorService.findByID(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Ator> findAll() {
        return atorService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@RequestBody @Valid Ator ator, @PathVariable Long id) throws AtorNotFoundException {
        return atorService.update(ator,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse deleteById(@PathVariable Long id) throws AtorNotFoundException {
        return atorService.deleteById(id);
    }

}
