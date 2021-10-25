package com.lipsoft.locacaofilmesapi.controller;

import com.lipsoft.locacaofilmesapi.dto.ClienteDTO;
import com.lipsoft.locacaofilmesapi.exceptions.ClienteNotFoundException;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import com.lipsoft.locacaofilmesapi.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse add(@RequestBody @Valid ClienteDTO clienteDTO) {
        return clienteService.add(clienteDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO find(@PathVariable Long id) throws ClienteNotFoundException {
        return clienteService.findByID(id);
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> findAll() {
        return clienteService.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@RequestBody @Valid ClienteDTO clienteDTO, @PathVariable Long id) throws ClienteNotFoundException {
        return clienteService.update(clienteDTO,id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse deleteById(@PathVariable Long id) throws ClienteNotFoundException {
        return clienteService.deleteById(id);
    }

}