package com.lipsoft.locacaofilmesapi.exceptions;

import java.time.LocalDateTime;

public class FilmeAlreadyRentedException extends Exception {

    public FilmeAlreadyRentedException(Long id, String movieName, LocalDateTime dataFimLocacao) {
        super(String.format("Filme já alugado com data para %s ! Favor, escolher outro \n", dataFimLocacao)
        + String.format("ID do filme = %s \n", id)
        + String.format("Nome do filme = %s", movieName));
    }
}
