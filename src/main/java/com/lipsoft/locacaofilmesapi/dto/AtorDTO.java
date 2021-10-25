package com.lipsoft.locacaofilmesapi.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtorDTO {

    private Long id;
    @NotNull
    @Size(min = 1, max = 200, message = "Nome do ator precisa ter entre 1 e 200 caracteres.")
    private String nome;
    @NotNull
    @Min(value = 1, message = "Idade não deve ser menor que 1")
    private Integer idade;
    @NotNull
    @Size(min = 1, max = 200, message = "Nome do personagem precisa ter entre 1 e 200 caracteres.")
    private String nomeDoPersonagem;



}
