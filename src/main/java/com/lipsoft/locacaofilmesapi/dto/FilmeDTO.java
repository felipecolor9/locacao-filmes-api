package com.lipsoft.locacaofilmesapi.dto;

import com.fasterxml.jackson.annotation.*;
import com.lipsoft.locacaofilmesapi.entities.Ator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmeDTO {

    @JsonIgnore
    private Long id;
    @NotNull
    @Size(min = 1, max = 200, message = "O nome do filme deve conter de 1 a 200 caracteres")
    @JsonProperty("title")
    private String nomeDoFilme;
    @NotNull
    @JsonProperty("plot")
    private String sinopse;
    @NotNull
    @JsonProperty("year")
    private int anoDeLancamento;
    @NotNull
    @JsonProperty("rating")
    private double notaDosUsuarios;
    @NotNull
    @JsonProperty("cast")
    private Collection<Ator> elenco;
}
