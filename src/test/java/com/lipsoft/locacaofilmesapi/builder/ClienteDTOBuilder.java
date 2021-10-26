package com.lipsoft.locacaofilmesapi.builder;

import com.lipsoft.locacaofilmesapi.dto.ClienteDTO;
import lombok.Builder;

@Builder
public class ClienteDTOBuilder {

    @Builder.Default
    private Long id = 1L;

    @Builder.Default
    private String nomeCompleto = "Michael Scott";

    @Builder.Default
    private int idade = 40;

    @Builder.Default
    private String estadoSigla = "PE";

    @Builder.Default
    private String cidade = "Recife";

    @Builder.Default
    private String complemento = "Apartamento 312";

    @Builder.Default
    private String cep = "12345-600";

    public ClienteDTO toClienteDTO() {
        return new ClienteDTO(id,
                nomeCompleto,
                idade,
                estadoSigla,
                cidade,
                complemento,
                cep);
    }
}
