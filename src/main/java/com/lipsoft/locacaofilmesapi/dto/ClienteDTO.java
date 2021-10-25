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
public class ClienteDTO {

    private Long id;
    @NotNull
    @Size(min = 4, max = 200, message = "O nome deve ter entre 4 a 200 caracteres")
    private String nomeCompleto;
    @NotNull
    @Min(value = 18, message = "A idade mínima para cadastro é de 18 anos")
    private Integer idade;
    @NotNull
    @Size(min = 2, max = 2)
    private String estadoSigla;
    @NotNull
    @Size(min = 4, max = 200, message="O nome da cidade deve ter entre 4 e 200 caracteres")
    private String cidade;
    @NotNull
    @Size(min = 1, max = 50, message = "Complemento deve ter entre 1 e 50 caracteres")
    private String complemento;
    @NotNull
    @Size(min = 9, max = 9, message = "O CEP deve conter 9 caracteres (XXXXX-XXX)")
    private String cep;
}
