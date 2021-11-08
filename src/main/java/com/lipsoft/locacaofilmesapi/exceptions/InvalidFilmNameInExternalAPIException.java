package com.lipsoft.locacaofilmesapi.exceptions;

public class InvalidFilmNameInExternalAPIException extends Exception {

    public InvalidFilmNameInExternalAPIException(String movieName) {super(String.format("Filme com nome %s não foi encontrado no banco de dados do IMDB", movieName));}
}