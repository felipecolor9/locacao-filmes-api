package com.lipsoft.locacaofilmesapi.controller;

import com.lipsoft.locacaofilmesapi.builder.FilmeDTOBuilder;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import com.lipsoft.locacaofilmesapi.services.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.lipsoft.locacaofilmesapi.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class FilmeControllerTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String MOVIE_API_PATH = "/api/filmes/";
    private static final long VALID_MOVIE_ID = 1L;
    private static final long INVALID_MOVIE_ID = 2L;

    private MockMvc mockMvc;

    @Mock
    private FilmeService filmeService;
    @InjectMocks
    private FilmeController filmeController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(filmeController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    @Test
    void whenGETIsCalledThenAMovieIsCreated() throws Exception {
        var filmeDTO = FilmeDTOBuilder.builder().build().toFilmeDTO();

        Mockito.lenient().when(filmeService.add(filmeDTO)).thenReturn(new MessageResponse());

        mockMvc.perform(post(MOVIE_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(filmeDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nomeDoFilme", is(filmeDTO.getNomeDoFilme())))
                .andExpect(jsonPath("$.anoDeLancamento", is(filmeDTO.getAnoDeLancamento())))
                .andExpect(jsonPath("$.notaDosUsuarios", is(filmeDTO.getNotaDosUsuarios())))
                .andExpect(jsonPath("$.notaDaCritica", is (filmeDTO.getNotaDaCritica())));
    }



}
