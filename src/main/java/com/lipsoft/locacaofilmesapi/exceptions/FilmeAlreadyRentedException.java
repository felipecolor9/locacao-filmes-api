package com.lipsoft.locacaofilmesapi.exceptions;

import java.time.LocalDateTime;

public class FilmeAlreadyRentedException extends Exception {

    public FilmeAlreadyRentedException(Long id, String movieName, LocalDateTime dataFimLocacao) {
        super(String.format("Filme '%s' com id %s já alugado com data para '%s'! Favor, escolher outro", movieName, id));
    }
}
