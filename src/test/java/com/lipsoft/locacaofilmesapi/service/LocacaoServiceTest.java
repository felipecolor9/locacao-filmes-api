package com.lipsoft.locacaofilmesapi.service;

import com.lipsoft.locacaofilmesapi.controller.LocacaoController;
import com.lipsoft.locacaofilmesapi.repository.ClienteRepository;
import com.lipsoft.locacaofilmesapi.repository.FilmeRepository;
import com.lipsoft.locacaofilmesapi.services.LocacaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@ExtendWith(MockitoExtension.class)
public class LocacaoServiceTest {

    private static final String BASE_URL = "http://localhost:8080";
    private static final String RENT_API_PATH = "/api/locacoes";
    private static final long VALID_MOVIE_ID = 1L;
    private static final long VALID_CLIENT_ID = 1L;
    private static final long INVALID_MOVIE_ID = 2L;
    private static final long INVALID_CLIENT_ID = 2L;

    private MockMvc mockMvc;

    FilmeRepository filmeRepository = Mockito.mock(FilmeRepository.class);
    ClienteRepository clienteRepository = Mockito.mock(ClienteRepository.class);
    LocacaoService locacaoService = Mockito.mock(LocacaoService.class);

    @InjectMocks
    private LocacaoController locacaoController;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(locacaoController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
                .build();
    }




}
