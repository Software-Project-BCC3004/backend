package br.es.pews.back.services;

import br.es.pews.back.dto.ProfissionalDTO;
import br.es.pews.back.models.Documento;
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
    
    
    
    public ResponseEntity<Profissional> getProfissionalByDocumento(Documento documento) {
        Optional<Profissional> profissional = profissionalRepository.findProfissionalByDocumento(documento);
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Profissional> getProfissionalByNome(String nomeProfissional) {
        System.out.println("🔍 Buscando profissional com nome: '" + nomeProfissional + "'");
    
        Optional<Profissional> profissional = profissionalRepository.findByNomeProfissional(nomeProfissional);
    
        if (profissional.isPresent()) {
            System.out.println("✅ Profissional encontrado: " + profissional.get().getNomeProfissional());
        } else {
            System.out.println("❌ Nenhum profissional encontrado para: '" + nomeProfissional + "'");
        }
    
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    

    public ResponseEntity<Profissional> createProfissional(@RequestBody Profissional profissional) {
        try {
            profissional.setSenhaProfissional(passwordEncoder.encode(profissional.getSenhaProfissional()));
            Profissional profissionalSalvo = profissionalRepository.save(profissional);
            return ResponseEntity.ok(profissionalSalvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Profissional> updateProfissional(@PathVariable Long id, @Valid @RequestBody ProfissionalDTO profissionalDTO) {
        Profissional profissionalUpdate = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional with ID " + id + " not found"));

        // Atualização manual dos campos
        if (profissionalDTO.documento() != null) {
            profissionalUpdate.setDocumento(profissionalDTO.documento());
        }
        if (profissionalDTO.nomeProfissional() != null && !profissionalDTO.nomeProfissional().isBlank()) {
            profissionalUpdate.setNomeProfissional(profissionalDTO.nomeProfissional());
        }
        if (profissionalDTO.funcao() != null && !profissionalDTO.funcao().isBlank()) {
            profissionalUpdate.setFuncao(profissionalDTO.funcao());
        }
        if (profissionalDTO.emailprofissional() != null && !profissionalDTO.emailprofissional().isBlank()) {
            profissionalUpdate.setEmailprofissional(profissionalDTO.emailprofissional());
        }
        if (profissionalDTO.senhaProfissional() != null && !profissionalDTO.senhaProfissional().isBlank()) {
            profissionalUpdate.setSenhaProfissional(passwordEncoder.encode(profissionalDTO.senhaProfissional())); // Garante criptografia
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
