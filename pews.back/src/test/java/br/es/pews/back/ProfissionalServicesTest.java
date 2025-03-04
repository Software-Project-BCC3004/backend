package br.es.pews.back;

import br.es.pews.back.models.Profissional;
import br.es.pews.back.repository.ProfissionalRepository;
import br.es.pews.back.services.ProfissionalServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfissionalServicesTest {


    @Mock
    private ProfissionalRepository profissionalRepository;

    @InjectMocks
    private ProfissionalServices profissionalServices;

    @Mock
    private PasswordEncoder passwordEncoder;

    private Profissional profissional;

    @BeforeEach
    @SuppressWarnings("deprecation")
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProfissionalSucesso() {
        Profissional profissional = new Profissional();
        profissional.setId(1L);
        profissional.setTipoDocumento("CRM");
        profissional.setNumeroDocumento("12345");
        profissional.setEstadoDocumento("PR");
        profissional.setNomeProfissional("nome teste");
        profissional.setFuncao("Médico");
        profissional.setEmail("test@test.com");
        profissional.setSenha_profissional("senha123");

        when(passwordEncoder.encode(profissional.getSenha_profissional())).thenReturn("senhaCodificada");

        when(profissionalRepository.save(any(Profissional.class))).thenReturn(profissional);
        System.out.println("Profissional antes de salvar: " + profissional);

        ResponseEntity<Profissional> response = profissionalServices.createProfissional(profissional);
        System.out.println("Resposta do método: " + response);


        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals(profissional, response.getBody());

        verify(passwordEncoder).encode("senha123");

        verify(profissionalRepository, times(1)).save(any(Profissional.class));
    }
}
