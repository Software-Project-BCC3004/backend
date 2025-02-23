package br.es.pews.back;

import br.es.pews.back.models.AvaliacaoPews;
import br.es.pews.back.form.Pontuacao;
import br.es.pews.back.repository.AvaliacaoPewsRepository;
import br.es.pews.back.services.AvaliacaoPewsServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvaliacaoPewsServiceTest {

    @InjectMocks
    private AvaliacaoPewsServices avaliacaoPewsServices;

    @Mock
    private AvaliacaoPewsRepository avaliacaoPewsRepository;

    @SuppressWarnings("deprecation")
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void calculoPEWStesteSucesso() {
        AvaliacaoPews avaliacaoPews = new AvaliacaoPews();
        avaliacaoPews.setAvaliacaoNeurologica(Pontuacao.AN2);
        avaliacaoPews.setAvaliacaoCardiovascular(Pontuacao.AN1);
        avaliacaoPews.setAvaliacaoRespiratoria(Pontuacao.AN3);
        avaliacaoPews.setEmese(Pontuacao.EmeseSIM);
        avaliacaoPews.setNebulizacao(Pontuacao.EmeseSIM);

        when(avaliacaoPewsRepository.save(any(AvaliacaoPews.class))).thenReturn(avaliacaoPews);

        AvaliacaoPews calculo = avaliacaoPewsServices.salvar(avaliacaoPews);

        assertNotNull(calculo);
        assertEquals(2+1+3+2+2, calculo.getPontuacaoTotal());
        verify(avaliacaoPewsRepository, times(1)).save(avaliacaoPews);
    }
}
