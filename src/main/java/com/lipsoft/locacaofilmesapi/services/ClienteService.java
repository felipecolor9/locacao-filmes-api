    package com.lipsoft.locacaofilmesapi.services;

import com.lipsoft.locacaofilmesapi.dto.ClienteDTO;
import com.lipsoft.locacaofilmesapi.exceptions.ClienteNotFoundException;
import com.lipsoft.locacaofilmesapi.mapper.ClienteMapper;
import com.lipsoft.locacaofilmesapi.repository.ClienteRepository;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

    @Service
public class ClienteService implements DbBasicsService<ClienteDTO> {
    private ClienteRepository clienteRepository;
    private MessageResponse messageResponse;
    private final ClienteMapper clienteMapper = ClienteMapper.INSTANCE;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, MessageResponse messageResponse) {
        this.clienteRepository = clienteRepository;
        this.messageResponse = messageResponse;
    }

    @Override
    public MessageResponse add(ClienteDTO clienteDTO) {
        var cliente = clienteRepository.save(clienteMapper.toModel(clienteDTO));
        return messageResponse.createMessageResponse("Criado o cliente com id= ", cliente.getId());
    }

    @Override
    public List<ClienteDTO> findAll() {
        return clienteRepository.findAll()
                .stream().map(clienteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClienteDTO findByID(Long id) throws ClienteNotFoundException {
        return clienteRepository.findById(id).map(clienteMapper::toDTO).orElseThrow(() -> new ClienteNotFoundException(id));
    }

    @Override
    public MessageResponse update(ClienteDTO clienteDTO, Long id) throws ClienteNotFoundException {
        var cliente = clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));

        cliente.setNomeCompleto(clienteDTO.getNomeCompleto());
        cliente.setIdade(clienteDTO.getIdade());
        cliente.setEstadoSigla(clienteDTO.getEstadoSigla());
        cliente.setCidade(clienteDTO.getCidade());
        cliente.setComplemento(clienteDTO.getComplemento());
        cliente.setCep(clienteDTO.getCep());

        clienteRepository.save(cliente);
        return messageResponse.createMessageResponse("Atualizado o cliente com o id= ", id);
    }

    @Override
    public MessageResponse deleteById(Long id) throws ClienteNotFoundException {
        clienteRepository.findById(id).orElseThrow(() -> new ClienteNotFoundException(id));
        clienteRepository.deleteById(id);
        return messageResponse.createMessageResponse("Deletado o cliente com id= ", id);
    }
}
