package br.es.pews.back.services;

import br.es.pews.back.models.Documento;
import br.es.pews.back.models.Profissional;
import br.es.pews.back.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfissionalServices {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public ResponseEntity<Profissional> getProfissionalById(Long id) {
        Optional<Profissional> profissional = profissionalRepository.findById(id);
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    public ResponseEntity<List<Profissional>> getAllProfissionals() {
        List<Profissional> profissionals = profissionalRepository.findAll();
        if (profissionals.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        else {
            return ResponseEntity.ok(profissionals);
        }
    }

    public ResponseEntity<Profissional> getProfissionalByDocumento(Documento documento) {
        Optional<Profissional> profissional = profissionalRepository.findProfissionalByDocumento(documento);
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Profissional> getProfissionalByNome(String nome) {
        Optional<Profissional> profissional = profissionalRepository.findByNome(nome);
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
