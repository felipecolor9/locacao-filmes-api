package com.lipsoft.locacaofilmesapi.service;

import com.lipsoft.locacaofilmesapi.builder.LocacaoBuilder;
import com.lipsoft.locacaofilmesapi.controller.LocacaoController;
import com.lipsoft.locacaofilmesapi.entities.Locacao;
import com.lipsoft.locacaofilmesapi.exceptions.ClienteNotFoundException;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeAlreadyRentedException;
import com.lipsoft.locacaofilmesapi.exceptions.FilmeNotFoundException;
import com.lipsoft.locacaofilmesapi.exceptions.InvalidRentDataException;
import com.lipsoft.locacaofilmesapi.repository.ClienteRepository;
import com.lipsoft.locacaofilmesapi.repository.FilmeRepository;
import com.lipsoft.locacaofilmesapi.repository.LocacaoRepository;
import com.lipsoft.locacaofilmesapi.response.MessageResponse;
import com.lipsoft.locacaofilmesapi.services.ClienteService;
import com.lipsoft.locacaofilmesapi.services.FilmeService;
import com.lipsoft.locacaofilmesapi.services.LocacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import java.time.LocalDateTime;

import static com.lipsoft.locacaofilmesapi.utils.JsonConvertionUtils.asJsonString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class LocacaoServiceTest {

    private static final String RENT_API_BASE_PATH = "/api/locacoes";
    private static final long VALID_MOVIE_ID = 1L;
    private static final long VALID_CLIENT_ID = 1L;
    private static final long VALID_RENT_ID = 1L;
    private static final long INVALID_MOVIE_ID = 2L;
    private static final long INVALID_CLIENT_ID = 2L;
    private static final LocalDateTime INVALID_DATE = LocalDateTime.now().minusDays(1);

    private MockMvc mockMvc;

    @Mock
    LocacaoRepository locacaoRepository;
    @Mock
    ClienteRepository clienteRepository;
    @Mock
    FilmeService filmeService;
    @Mock
    LocacaoService locacaoService;

    @InjectMocks
    private LocacaoController locacaoController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(locacaoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }

    //Business logic tests
    @Test
    void whenRentMovieWithValidsIDsThenItShouldBeCreatedAndCreatedStatus() throws Exception {
        var locacaoBeforeRent = LocacaoBuilder.builder().build().toBasicLocacao();
        var locacaoAfterRent = LocacaoBuilder.builder().build().toBoundLocacao();

        when(locacaoController.rent(locacaoBeforeRent, VALID_MOVIE_ID, VALID_CLIENT_ID))
                .thenReturn(new MessageResponse());

        mockMvc.perform(post(RENT_API_BASE_PATH +"/filme/"+VALID_MOVIE_ID+"/cliente/"+VALID_CLIENT_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8")
                        .content(asJsonString(locacaoAfterRent)))
                .andExpect(status().isCreated())
                .andDo(MockMvcResultHandlers.print());
    }


}
