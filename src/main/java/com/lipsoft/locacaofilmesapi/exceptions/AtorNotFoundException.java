package com.lipsoft.locacaofilmesapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AtorNotFoundException extends Exception {

    public AtorNotFoundException(Long id) {
        super(String.format("Ator com id %s não encontrado!", id));
    }
}
