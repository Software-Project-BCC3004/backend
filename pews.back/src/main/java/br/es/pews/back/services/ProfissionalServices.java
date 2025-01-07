package br.es.pews.back.services;

import br.es.pews.back.dto.ProfissionalDTO;
import br.es.pews.back.models.Documento;
import br.es.pews.back.models.Profissional;
import br.es.pews.back.repository.ProfissionalRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    private ModelMapper modelMapper;

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

    public ResponseEntity<Profissional> createProfissional(@RequestBody Profissional profissional) {
        try {
            Profissional profissionalSalvo = profissionalRepository.save(profissional);
            return ResponseEntity.ok(profissionalSalvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<Profissional> updateProfissional(@PathVariable Long id, @Valid @RequestBody ProfissionalDTO profissionalDTO) {
        Profissional profissionalUpdate = profissionalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profissional with ID " + id + " not found"));

        modelMapper.map(profissionalDTO, profissionalUpdate);

        profissionalRepository.save(profissionalUpdate);
        return ResponseEntity.ok(profissionalUpdate);
    }

    public ResponseEntity<Profissional> deleteProfissional(@PathVariable Long id) {
        Profissional profissionalDelete = profissionalRepository.findById(id).orElseThrow(() -> new RuntimeException("Profissional with ID " + id + " not found"));
        profissionalRepository.delete(profissionalDelete);
        return ResponseEntity.ok(profissionalDelete);
    }
}
