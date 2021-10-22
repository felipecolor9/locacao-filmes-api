package com.lipsoft.locacaofilmesapi.services;

import com.lipsoft.locacaofilmesapi.entities.Cliente;
import com.lipsoft.locacaofilmesapi.exceptions.ClienteNotFoundException;
import com.lipsoft.locacaofilmesapi.repository.ClienteRepository;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class ClienteService implements DbBasicsService<Cliente> {
    private ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public MessageResponse add(Cliente cliente) {
        cliente.setEstadoSigla(cliente.getEstadoSigla().toUpperCase().trim());
        clienteRepository.save(cliente);
        return createMessageResponse("Criado o cliente com id= ",cliente.getId());
    }

    @Override
    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente findByID(Long id) throws ClienteNotFoundException {
        return clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @Override
    public MessageResponse update(Cliente cliente, Long id) throws ClienteNotFoundException {
        var clienteToBeUpdated = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));

        clienteToBeUpdated.setNomeCompleto(cliente.getNomeCompleto());
        clienteToBeUpdated.setIdade(cliente.getIdade());
        clienteToBeUpdated.setEstadoSigla(cliente.getEstadoSigla());
        clienteToBeUpdated.setCidade(cliente.getCidade());
        clienteToBeUpdated.setComplemento(cliente.getComplemento());
        clienteToBeUpdated.setCep(cliente.getCep());

        clienteRepository.save(clienteToBeUpdated);
        return createMessageResponse("Atualizado o cliente com o id= ",id);
    }

    @Override
    public MessageResponse deleteById(Long id) throws ClienteNotFoundException {
        clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
        clienteRepository.deleteById(id);
        return createMessageResponse("Deletado o cliente com id= ",id);
    }

    public MessageResponse createMessageResponse(String msg, Long id) {
        return new MessageResponse.MessageResponseBuilder()
                .addMessage(msg)
                .addIdObj(id)
                .build();
    }
}
