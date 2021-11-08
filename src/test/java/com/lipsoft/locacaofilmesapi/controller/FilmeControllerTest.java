package com.lipsoft.locacaofilmesapi.controller;

import com.lipsoft.locacaofilmesapi.builder.FilmeDTOBuilder;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeNotFoundException;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import com.lipsoft.locacaofilmesapi.services.FilmeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import static com.lipsoft.locacaofilmesapi.utils.JsonConvertionUtils.asJsonString;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(MockitoExtension.class)
public class FilmeControllerTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String MOVIE_API_PATH = "/api/filmes";
    private static final long VALID_MOVIE_ID = 1L;
    private static final long INVALID_MOVIE_ID = 2L;

    private MockMvc mockMvc;

    FilmeService filmeService = Mockito.mock(FilmeService.class);
    @InjectMocks
    private FilmeController filmeController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(filmeController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    //API crud functions example tests
//    @Test
//    void whenPOSTIsCalledThenAMovieIsCreated() throws Exception {
//        var mockedMovie = FilmeDTOBuilder.builder().build().toFilmeDTO();
//
//        when(filmeService.add(mockedMovie)).thenReturn(new MessageResponse());
//        reset(filmeService);
//
//        mockMvc.perform(post(MOVIE_API_PATH)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(mockedMovie)))
//                .andExpect(status().isCreated())
//                .andDo(MockMvcResultHandlers.print());
//    }
//
//    @Test
//    void whenPOSTIsCalledWithoutRequiredFieldThenAnErrorIsReturned() throws Exception {
//        var mockedMovie = FilmeDTOBuilder.builder().nomeDoFilme(null).build().toFilmeDTO();
//
//        mockMvc.perform(post(MOVIE_API_PATH)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(asJsonString(mockedMovie))
//                .characterEncoding("utf-8"))
//                .andExpect(status().isBadRequest());
//    }
//
//    @Test
//    void whenGETIsCalledWithValidIdThenOkStatusAndMovieIsReturned() throws Exception {
//
//        var mockedMovie = FilmeDTOBuilder.builder().build().toFilmeDTO();
//        when(filmeService.findByID(mockedMovie.getId())).thenReturn(mockedMovie);
//
//        mockMvc.perform(get(MOVIE_API_PATH + "/" + mockedMovie.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .characterEncoding("utf-8"))
//                .andExpect(status().isOk())
//                .andDo(MockMvcResultHandlers.print())
//                .andExpect(jsonPath("$.nomeDoFilme", is(mockedMovie.getNomeDoFilme())))
//                .andExpect(jsonPath("$.anoDeLancamento", is(mockedMovie.getAnoDeLancamento())))
//                .andExpect(jsonPath("$.notaDosUsuarios", is(mockedMovie.getNotaDosUsuarios())))
//                .andExpect(jsonPath("$.notaDaCritica", is(mockedMovie.getNotaDaCritica())));
//    }
//
//    @Test
//    void whenGETIsCalledWithInvalidIdThenNotFoundExceptionAndStatusIsReturned() throws Exception {
//        var mockedMovie = FilmeDTOBuilder.builder().build().toFilmeDTO();
//        when(filmeService.findByID(INVALID_MOVIE_ID)).thenThrow(new FilmeNotFoundException(INVALID_MOVIE_ID));
//
//        mockMvc.perform(get(MOVIE_API_PATH + "/" + INVALID_MOVIE_ID)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
//
//    @Test
//    void whenDELETEIsCalledWithValidIdThenNoContentStatusIsReturned() throws Exception {
//
//        var mockedMovie = FilmeDTOBuilder.builder().build().toFilmeDTO();
//        when(filmeService.deleteById(VALID_MOVIE_ID)).thenReturn(new MessageResponse());
//
//        mockMvc.perform(delete(MOVIE_API_PATH + "/" + mockedMovie.getId())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNoContent());
//    }
//
//    @Test
//    void whenDELETEIsCalledWithInvalidIdThenNotFoundExceptionAndStatusIsReturned() throws Exception {
//
//        var mockedMovie = FilmeDTOBuilder.builder().build().toFilmeDTO();
//        when(filmeService.deleteById(INVALID_MOVIE_ID)).thenThrow(new FilmeNotFoundException(INVALID_MOVIE_ID));
//
//        mockMvc.perform(delete(MOVIE_API_PATH + "/" + INVALID_MOVIE_ID)
//                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isNotFound());
//    }
}
