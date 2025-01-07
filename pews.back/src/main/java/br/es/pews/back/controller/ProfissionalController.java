package br.es.pews.back.controller;

import br.es.pews.back.dto.ProfissionalDTO;
import br.es.pews.back.models.Documento;
import br.es.pews.back.models.Profissional;
import br.es.pews.back.services.ProfissionalServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProfissionalController {

    @Autowired
    private ProfissionalServices profissionalServices;

    @GetMapping
    public ResponseEntity<Profissional> getProfissionalById(Long id) {
        return profissionalServices.getProfissionalById(id);
    }

    @GetMapping
    public ResponseEntity<List<Profissional>> getAllProfissionals() {return profissionalServices.getAllProfissionals();}

    @GetMapping
    public ResponseEntity<Profissional> getProfissionalByDocumento(Documento documeto){
        return profissionalServices.getProfissionalByDocumento(documeto);
    }

    @GetMapping
    public ResponseEntity<Profissional> getProfissionalByNome(String nome){
        return profissionalServices.getProfissionalByNome(nome);
    }

    @PostMapping
    public ResponseEntity<Profissional> createProfissional(@RequestBody Profissional profissional){
        return profissionalServices.createProfissional(profissional);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profissional> updateProfissional(@PathVariable Long id, @Valid @RequestBody ProfissionalDTO profissionalDTO){
        return profissionalServices.updateProfissional(id, profissionalDTO);
    }
}
