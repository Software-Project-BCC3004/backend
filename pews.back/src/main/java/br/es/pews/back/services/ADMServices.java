package br.es.pews.back.services;

import br.es.pews.back.dto.AdmDTO;
import br.es.pews.back.models.ADM;
import br.es.pews.back.repository.ADMRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ADMServices {

    private final ADMRepository admRepository;
    private final PasswordEncoder passwordEncoder;

    public ADMServices(ADMRepository admRepository, PasswordEncoder passwordEncoder) {
        this.admRepository = admRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public ResponseEntity<ADM> save(ADM adm) {
        try {
            adm.setSenha_adm(passwordEncoder.encode(adm.getSenha_adm()));
            ADM admSaved = admRepository.save(adm);
            return ResponseEntity.ok(admSaved);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Transactional
    public ResponseEntity<ADM> update(Long id, AdmDTO admDTO) {
        ADM admUpdated = admRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ADM with ID: " + id + " not found"));

        // Atualiza manualmente os campos importantes
        if (admDTO.email() != null && !admDTO.email().isEmpty()) {
            admUpdated.setEmail(admDTO.email());
        }

        if (admDTO.senha() != null && !admDTO.senha().isEmpty()) {
            admUpdated.setSenha_adm(passwordEncoder.encode(admDTO.senha()));
        }

        // Persiste no banco de dados
        ADM admSaved = admRepository.save(admUpdated);
        return ResponseEntity.ok(admSaved);
    }

    @Transactional
    public ResponseEntity<ADM> delete(Long id) {
        ADM admDelete = admRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ADM with ID: " + id + " not found"));
        admRepository.delete(admDelete);
        return ResponseEntity.ok(admDelete);
    }
}
