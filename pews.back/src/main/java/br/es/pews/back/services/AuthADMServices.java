package br.es.pews.back.services;

import br.es.pews.back.dto.AdmDTO;
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

    
    public ADM createADM(AdmDTO admDTO) {
        ADM adm = new ADM();
        adm.setEmailADM(admDTO.emailADM());

        
        adm.setPassword(passwordEncoder.encode(admDTO.senhaADM()));

        return admRepository.save(adm);
    }

    
    public ADM updateADMPassword(Long id, String novaSenha) {
        ADM adm = admRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ADM não encontrado"));

        
        adm.setPassword(passwordEncoder.encode(novaSenha));

        return admRepository.save(adm);
    }

    
    public String login(LoginDTO loginDTO) {
        ADM adm = admRepository.findByEmailADM(loginDTO.email())
                .orElseThrow(() -> new RuntimeException("Credenciais incorretas"));

        
        if (!passwordEncoder.matches(loginDTO.senha(), adm.getPassword())) {
            throw new RuntimeException("Senha incorreta");
        }

        return jwtsService.generateToken(adm.getEmailADM());
    }
}
