package com.lipsoft.locacaofilmesapi.controller;

import com.lipsoft.locacaofilmesapi.dto.ClienteDTO;
import com.lipsoft.locacaofilmesapi.exceptions.ClienteNotFoundException;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import com.lipsoft.locacaofilmesapi.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Cliente")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso na Operação"),
        @ApiResponse(code = 400, message = "Operação invalida; Geralmente ocorre por algum campo inválido"),
        @ApiResponse(code = 401, message = "Sem autorização; Falta de credenciais de autenticação"),
        @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
        @ApiResponse(code = 404, message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Foi gerada uma 'exception'; Erro interno do servidor"),
})

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private ClienteService clienteService;

    private final String DOC_POST_OPERATION = "Cria um cliente e o adiciona no banco de dados.";
    private final String DOC_POST_OPERATION_LIST = "Adiciona uma lista de clientes e a adiciona no banco de dados.";
    private final String DOC_GET_OPERATION = "Retorna um cliente registrado no banco de dados de acordo com o seu código (ID)";
    private final String DOC_GET_OPERATION_LIST = "Retorna todas os clientes registrados no banco de dados.";
    private final String DOC_PUT_OPERATION = "Edita e salva no banco de dados todas as mudancas do cliente de acordo com o seu código (ID)";
    private final String DOC_DELETE_OPERATION = "Exclui um cliente do banco de dados de acordo com o seu código (ID)";

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @ApiOperation(value = DOC_POST_OPERATION)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse add(@RequestBody @Valid ClienteDTO clienteDTO) {
        return clienteService.add(clienteDTO);
    }

    @ApiOperation(value = DOC_GET_OPERATION)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDTO find(@PathVariable Long id) throws ClienteNotFoundException {
        return clienteService.findByID(id);
    }

    @ApiOperation(value = DOC_GET_OPERATION_LIST)
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ClienteDTO> findAll() {
        return clienteService.findAll();
    }

    @ApiOperation(value = DOC_PUT_OPERATION)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@RequestBody @Valid ClienteDTO clienteDTO, @PathVariable Long id) throws ClienteNotFoundException {
        return clienteService.update(clienteDTO,id);
    }

    @ApiOperation(value = DOC_DELETE_OPERATION)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse deleteById(@PathVariable Long id) throws ClienteNotFoundException {
        return clienteService.deleteById(id);
    }

}