package com.lipsoft.locacaofilmesapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.DateFormat;
import java.time.LocalDateTime;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRentDataException extends Exception{

    public InvalidRentDataException(LocalDateTime data) { super(String.format("Data inválida para aluguel. Escolher data posterior a " + data)); }
}
