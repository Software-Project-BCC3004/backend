package br.es.pews.back.services;

import br.es.pews.back.models.Profissional;
import br.es.pews.back.repository.ProfissionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfissionalServices {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public ResponseEntity<Profissional> getProfissionalById(Long id) {
        Optional<Profissional> profissional = profissionalRepository.findById(id);
        return profissional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
