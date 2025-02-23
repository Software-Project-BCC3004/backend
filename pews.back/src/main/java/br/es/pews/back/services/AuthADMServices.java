package br.es.pews.back.services;

import br.es.pews.back.dto.LoginDTO;
import br.es.pews.back.models.ADM;
import br.es.pews.back.repository.ADMRepository;
import br.es.pews.back.security.JwtsService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
public class AuthADMServices {

    @Autowired
    private ADMRepository admRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtsService jwtsService;

    public String login(LoginDTO loginDTO) {
        ADM adm = admRepository.findByEmailADM(loginDTO.email())
                .orElseThrow(() -> new RuntimeException("Credenciais incorretas"));

        if(!passwordEncoder.matches(loginDTO.senha(), adm.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }
        return jwtsService.generateToken(adm.getEmailADM());
    }
}
