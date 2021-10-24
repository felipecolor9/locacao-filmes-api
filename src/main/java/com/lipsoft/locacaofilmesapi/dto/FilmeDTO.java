package com.lipsoft.locacaofilmesapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmeDTO {

    private Long id;

    @NotNull
    @Size(min = 1, max = 200)
    private String nomeDoFilme;
    @NotNull
    @Size(min = 4, max = 4)
    @Min(value = 1900, message = "O ano de lançamento não deve ser anterior a 1900")
//  @Max(value = 2021, message = "O ano de lançamento não deve ser posterior ao ano atual(2021)") -> Tratamento via front-end
    private Integer anoDeLancamento;
    @NotNull
    @Max(value = 10, message = "A nota deve estar no valor entre 0 e 10")
    private double notaDosUsuarios;
    @NotNull
    @Max(value = 10, message = "A nota deve estar no valor entre 0 e 10")
    private double notaDaCritica;
}
