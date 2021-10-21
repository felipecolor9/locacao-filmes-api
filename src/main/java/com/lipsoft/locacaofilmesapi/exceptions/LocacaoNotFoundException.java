package com.lipsoft.locacaofilmesapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class LocacaoNotFoundException extends Exception {

    public LocacaoNotFoundException(Long id) {
        super(String.format("Locacao com id %s não encontrado!", id));
    }
}
