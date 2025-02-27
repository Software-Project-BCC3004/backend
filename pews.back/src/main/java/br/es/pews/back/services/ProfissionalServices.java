package br.es.pews.back.services;

import br.es.pews.back.dto.ProfissionalDTO;
import br.es.pews.back.models.Profissional;
import br.es.pews.back.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalServices {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ResponseEntity<Profissional> getProfissionalById(Long id) {
        Optional<Profissional> profissional = profissionalRepository.findById(id);
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    public ResponseEntity<List<Profissional>> getAllProfissionals() {
        List<Profissional> profissionals = profissionalRepository.findAll();
        if (profissionals.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profissionals);
    }


    public ResponseEntity<List<Profissional>> getProfissionaisByPrimeiroNome(String primeiroNome) {
        List<Profissional> profissionais = profissionalRepository.findByPrimeiroNome(primeiroNome);
        if (profissionais.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profissionais);
    }


    public ResponseEntity<List<Profissional>> getProfissionalByNome(String nomeProfissional) {
        List<Profissional> profissionals = profissionalRepository.findByNomeProfissional(nomeProfissional);
        return profissionals.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(profissionals);
    }
    

    public ResponseEntity<List<Profissional>> getProfissionaisByTipoDocumento(String tipoDocumentoProfissional) {
        List<Profissional> profissionais = profissionalRepository.findByTipoDocumento(tipoDocumentoProfissional);
        if (profissionais.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(profissionais);
    }
    

    public ResponseEntity<List<Profissional>> getProfissionalByNumeroDocumento(String numeroDocumentoProfissional) {
        List<Profissional> profissionals = profissionalRepository.findByNumeroDocumento(numeroDocumentoProfissional);
        return profissionals.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(profissionals);
    }

    public ResponseEntity<List<Profissional>> getProfissionalByEstadoDocumento(String estadoDocumentoProfissional) {
        List<Profissional> profissionals = profissionalRepository.findByEstadoDocumento(estadoDocumentoProfissional);
        return profissionals.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(profissionals);
    }

    public ResponseEntity<List<Profissional>> getProfissionalByFuncao(String funcao){
        List<Profissional> profissionals = profissionalRepository.findProfissionalByFuncao(funcao);
        return profissionals.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(profissionals);
    }

    public ResponseEntity<Profissional> createProfissional(@RequestBody Profissional profissional) {
        try {
            profissional.setSenha_profissional(passwordEncoder.encode(profissional.getSenha_profissional()));
            Profissional profissionalSalvo = profissionalRepository.save(profissional);
            return ResponseEntity.ok(profissionalSalvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Profissional> updateProfissional(@PathVariable Long id, @Valid @RequestBody ProfissionalDTO profissionalDTO) {
        Profissional profissionalUpdate = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional with ID " + id + " not found"));

        if (profissionalDTO.estadoDocumento() != null && !profissionalUpdate.getEstadoDocumento().isBlank()) {
            profissionalUpdate.setEstadoDocumento(profissionalDTO.estadoDocumento());
        }

        if (profissionalDTO.numeroDocumento() != null && !profissionalDTO.numeroDocumento().isBlank()) {
            profissionalUpdate.setNumeroDocumento(profissionalDTO.numeroDocumento());
        }

        if (profissionalDTO.tipoDocumento() != null && !profissionalDTO.tipoDocumento().isBlank()) {
            profissionalUpdate.setNomeProfissional(profissionalDTO.tipoDocumento());
        }

        if (profissionalDTO.nomeProfissional() != null && !profissionalDTO.nomeProfissional().isBlank()) {
            profissionalUpdate.setNomeProfissional(profissionalDTO.nomeProfissional());
        }
        if (profissionalDTO.funcao() != null && !profissionalDTO.funcao().isBlank()) {
            profissionalUpdate.setFuncao(profissionalDTO.funcao());
        }
        if (profissionalDTO.email() != null && !profissionalDTO.email().isBlank()) {
            profissionalUpdate.setEmail(profissionalDTO.email());
        }
        if (profissionalDTO.senha() != null && !profissionalDTO.senha().isBlank()) {
            profissionalUpdate.setSenha_profissional(passwordEncoder.encode(profissionalDTO.senha()));
        }

        profissionalRepository.save(profissionalUpdate);
        return ResponseEntity.ok(profissionalUpdate);
    }

    public ResponseEntity<Profissional> deleteProfissional(@PathVariable Long id) {
        Profissional profissionalDelete = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional with ID " + id + " not found"));
        profissionalRepository.delete(profissionalDelete);
        return ResponseEntity.ok(profissionalDelete);
    }
}
