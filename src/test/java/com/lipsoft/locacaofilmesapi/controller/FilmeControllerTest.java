package com.lipsoft.locacaofilmesapi.controller;

import com.lipsoft.locacaofilmesapi.builder.FilmeDTOBuilder;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import com.lipsoft.locacaofilmesapi.services.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.lipsoft.locacaofilmesapi.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(MockitoExtension.class)
public class FilmeControllerTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String MOVIE_API_PATH = "/api/filmes";
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
    void whenPOSTIsCalledThenAMovieIsCreated() throws Exception {
        var mockFilme = FilmeDTOBuilder.builder().build().toFilmeDTO();

        when(filmeService.add(mockFilme)).thenReturn(new MessageResponse());
        reset(filmeService);

        mockMvc.perform(post(MOVIE_API_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(mockFilme)))
                .andExpect(status().isCreated());
    }

    @Test
    void whenGETIsCalledThenOkStatusIsReturned() throws Exception {

        var mockFilme = FilmeDTOBuilder.builder().build().toFilmeDTO();
        when(filmeService.findByID(mockFilme.getId())).thenReturn(mockFilme);
        reset(filmeService);

        mockMvc.perform(get(MOVIE_API_PATH + "/" + mockFilme.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nomeDoFilme", is(mockFilme.getNomeDoFilme())))
                .andExpect(jsonPath("$.anoDeLancamento", is(mockFilme.getAnoDeLancamento())))
                .andExpect(jsonPath("$.notaDosUsuarios", is(mockFilme.getNotaDosUsuarios())))
                .andExpect(jsonPath("$.notaDaCritica", is(mockFilme.getNotaDaCritica())));
    }



}
