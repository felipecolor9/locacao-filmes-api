package com.lipsoft.locacaofilmesapi.builder;

import com.lipsoft.locacaofilmesapi.dto.FilmeDTO;
import com.lipsoft.locacaofilmesapi.entities.Filme;
import lombok.Builder;

@Builder
public class FilmeDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nomeDoFilme = "Sexta-feira em apuros";

    @Builder.Default
    private int anoDeLancamento = 1995;

    @Builder.Default
    private double notaDosUsuarios = 9.7;

    @Builder.Default
    private double notaDaCritica = 7.8;

    public FilmeDTO toFilmeDTO() {
        return new FilmeDTO(id, nomeDoFilme, anoDeLancamento, notaDosUsuarios, notaDaCritica);
    }

    public Filme toFilme() {
        return new Filme(id, nomeDoFilme, anoDeLancamento, notaDosUsuarios, notaDaCritica);
    }

}
