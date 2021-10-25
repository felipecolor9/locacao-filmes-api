package com.lipsoft.locacaofilmesapi.controller;

import com.lipsoft.locacaofilmesapi.dto.FilmeDTO;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeNotFoundException;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import com.lipsoft.locacaofilmesapi.services.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


import javax.validation.Valid;
import java.util.List;

@Api(value = "Filme")
@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Sucesso na Operação"),
        @ApiResponse(code = 400, message = "Operação invalida; Geralmente ocorre por algum campo inválido"),
        @ApiResponse(code = 401, message = "Sem autorização; Falta de credenciais de autenticação"),
        @ApiResponse(code = 403, message = "Sem permissão para acessar este recurso"),
        @ApiResponse(code = 404, message = "Não encontrado"),
        @ApiResponse(code = 500, message = "Foi gerada uma 'exception'; Erro interno do servidor"),
})

@RestController
@RequestMapping("/api/filmes")
public class FilmeController {

    @Autowired
    FilmeService filmeService;

    private final String DOC_POST_OPERATION = "Cria um filme e o adiciona no banco de dados.";
    private final String DOC_POST_OPERATION_LIST = "Adiciona uma lista de filmes e a adiciona no banco de dados.";
    private final String DOC_GET_OPERATION = "Retorna um filme registrado no banco de dados de acordo com o seu código (ID)";
    private final String DOC_GET_OPERATION_LIST = "Retorna todas os filmes registrados no banco de dados.";
    private final String DOC_PUT_OPERATION = "Edita e salva no banco de dados todas as mudancas do filme de acordo com o seu código (ID)";
    private final String DOC_DELETE_OPERATION = "Exclui um filme do banco de dados de acordo com o seu código (ID)";

    @ApiOperation(value = DOC_POST_OPERATION)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse add(@RequestBody @Valid FilmeDTO filmeDTO) {
        return filmeService.add(filmeDTO);
    }

    @ApiOperation(value = DOC_GET_OPERATION)
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public FilmeDTO find(@PathVariable Long id) throws FilmeNotFoundException {
        return filmeService.findByID(id);
    }


    @ApiOperation(value = DOC_GET_OPERATION_LIST)
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<FilmeDTO> findAll() {
        return filmeService.findAll();
    }


    @ApiOperation(value = DOC_PUT_OPERATION)
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse update(@RequestBody @Valid FilmeDTO filmeDTO, @PathVariable Long id) throws FilmeNotFoundException {
        return filmeService.update(filmeDTO ,id);
    }

    @ApiOperation(value = DOC_DELETE_OPERATION)
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MessageResponse delete(@PathVariable Long id) throws FilmeNotFoundException {
        return filmeService.deleteById(id);
    }
}
