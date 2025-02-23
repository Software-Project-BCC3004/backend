package br.es.pews.back.services;

import br.es.pews.back.dto.LoginDTO;
import br.es.pews.back.models.Profissional;
import br.es.pews.back.repository.ProfissionalRepository;
import br.es.pews.back.security.JwtsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Setter
@Getter
public class AuthProfissionalServices {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtsService jwtsService;

    public String login(LoginDTO loginDTO) {
        Profissional profissional = profissionalRepository.findByEmailprofissional(loginDTO.email())
                .orElseThrow(()-> new RuntimeException("Credenciais incorretas"));

        if (!passwordEncoder.matches(loginDTO.senha(), profissional.getSenhaProfissional())) {
           throw new RuntimeException("Senha incorreta");
        }
        return jwtsService.generateToken(profissional.getEmailprofissional());
    }
}
