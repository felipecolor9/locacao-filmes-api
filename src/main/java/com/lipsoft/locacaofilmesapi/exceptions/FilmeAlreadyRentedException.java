package com.lipsoft.locacaofilmesapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FilmeAlreadyRentedException extends Exception{

    public FilmeAlreadyRentedException(Long id) { super(String.format("Filme com ID= %d já alugado em sua conta! Favor consultar data de locação.",id)); }
}
