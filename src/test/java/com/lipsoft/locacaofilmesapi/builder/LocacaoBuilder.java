package com.lipsoft.locacaofilmesapi.builder;

import com.lipsoft.locacaofilmesapi.entities.Cliente;
import com.lipsoft.locacaofilmesapi.entities.Filme;
import com.lipsoft.locacaofilmesapi.entities.Locacao;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class LocacaoBuilder {

    @Builder.Default
    private Long id= 1L;

    @Builder.Default
    private Filme filme = FilmeDTOBuilder.builder().build().toFilme();

    @Builder.Default
    private Cliente cliente = ClienteDTOBuilder.builder().build().toCliente();

    @Builder.Default
    private LocalDateTime dataInicioLocacao = LocalDateTime.now();

    //Valid date by default, change when build on tests.
    @Builder.Default
    private LocalDateTime dataFimLocacao = LocalDateTime.now().plusDays(1);

    @Builder.Default
    private double valorTotal = 19.20;

    //Mock rent without client and movie ID
    public Locacao toBasicLocacao() {
        return new Locacao(id,
                dataInicioLocacao,
                dataFimLocacao,
                valorTotal);
    }

    //Mock rent with Client and Movie
    public Locacao toBoundLocacao() {
        return new Locacao(id,
                filme,
                cliente,
                dataInicioLocacao,
                dataFimLocacao,
                valorTotal);
    }

}
