package com.lipsoft.locacaofilmesapi.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.lipsoft.locacaofilmesapi.dto.FilmeDTO;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeNotFoundException;
import com.lipsoft.locacaofilmesapi.exceptions.InvalidFilmNameInExternalAPIException;
import com.lipsoft.locacaofilmesapi.mapper.FilmeMapper;
import com.lipsoft.locacaofilmesapi.repository.FilmeRepository;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FilmeService {

    private final FilmeMapper filmeMapper = FilmeMapper.INSTANCE;
    private FilmeRepository filmeRepository;
    private final MessageResponse messageResponse;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Autowired
    public FilmeService(FilmeRepository filmeRepository, MessageResponse messageResponse, RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.filmeRepository = filmeRepository;
        this.messageResponse = messageResponse;
        this.restTemplate = restTemplateBuilder.build();
        this.objectMapper = objectMapper;
    }

    public MessageResponse add(String movieName) throws InvalidFilmNameInExternalAPIException {
        var filmeImdbAPI = requestMovieInfo(movieName);
        filmeImdbAPI.setElenco(new ArrayList<>());
        var savedFilme = filmeRepository.save(filmeMapper.toModel(filmeImdbAPI));
        return messageResponse.createMessageResponse("Criado o filme com id= ", savedFilme.getId());
    }

    private FilmeDTO requestMovieInfo(String movieName) throws InvalidFilmNameInExternalAPIException {
        var headers = new HttpHeaders();
        var gson = new Gson();
        var url = "https://imdb-internet-movie-database-unofficial.p.rapidapi.com/film/"+movieName;
        headers.add("x-rapidapi-host", "imdb-internet-movie-database-unofficial.p.rapidapi.com");
        headers.add("x-rapidapi-key", "cc90d47931mshb0e2c470d29ae89p1408c1jsn206319c88e7f");
        headers.add("Content-Type","application/json");
        var request = new HttpEntity<String>(headers);

        var response= restTemplate.exchange(url, HttpMethod.GET, request, FilmeDTO.class);
        var jsonResponse = gson.toJson(response.getBody());
        var filme = gson.fromJson(jsonResponse, FilmeDTO.class);

        if (filme.getNomeDoFilme().isBlank()) throw new InvalidFilmNameInExternalAPIException(movieName);
        if (response.getStatusCode() == HttpStatus.OK) log.info("200 OK");

    return response.getBody();
    }

    public List<FilmeDTO> findAll() {
        return filmeRepository.findAll().stream()
                .map(filmeMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FilmeDTO findByID(Long id) throws FilmeNotFoundException {
        return filmeRepository.findById(id).map(filmeMapper::toDTO).orElseThrow(() -> new FilmeNotFoundException(id));
    }

    public MessageResponse deleteById(Long id) throws FilmeNotFoundException {
        filmeRepository.findById(id).orElseThrow(() -> new FilmeNotFoundException(id));
        filmeRepository.deleteById(id);
        return messageResponse.createMessageResponse("Deletado o filme com id= ", id);
    }
}
