package br.es.pews.back.controller;

import br.es.pews.back.dto.ProfissionalDTO;
import br.es.pews.back.models.Profissional;
import br.es.pews.back.services.ProfissionalServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    private ProfissionalServices profissionalServices;

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Profissional> getProfissionalById(@PathVariable Long id) {
        return profissionalServices.getProfissionalById(id);
    }

    @GetMapping("/consultar/todos")
    public ResponseEntity<List<Profissional>> getAllProfissionals() {return profissionalServices.getAllProfissionals();}

    @GetMapping("/consultar/documento/tipo/{tipo}")
    public ResponseEntity<Profissional> getProfissionalBytipoDocumentoProfissional(@PathVariable String tipoDocumentoProfissional) {
        return profissionalServices.getProfissionalBytipoDocumentoProfissional(tipoDocumentoProfissional);
    }

    @GetMapping("/consultar/documento/numero/{numero}")
    public ResponseEntity<Profissional> getProfissionalByNumeroDocumentoProfissional(@PathVariable String numeroDocumento) {
        return profissionalServices.getProfissionalByNumeroDocumentoProfissional(numeroDocumento);
    }

    @GetMapping("/consultar/documento/estado/{estado}")
    public ResponseEntity<Profissional> getProfissionalByEstadoDocumentoProfissional(@PathVariable String estadoDocumentoProfissional) {
        return profissionalServices.getProfissionalByEstadoDocumentoProfissional(estadoDocumentoProfissional);
    }

    @GetMapping("/consultar/nome/{nome}")
    public ResponseEntity<Profissional> getProfissionalByNome(@PathVariable String nome){
        return profissionalServices.getProfissionalByNome(nome);
    }

    @PostMapping("/criar")
    public ResponseEntity<Profissional> createProfissional(@RequestBody Profissional profissional){
        return profissionalServices.createProfissional(profissional);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Profissional> updateProfissional(@PathVariable Long id,
                                                           @Valid @RequestBody ProfissionalDTO profissionalDTO){
        return profissionalServices.updateProfissional(id, profissionalDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Profissional> deleteProfissional (@PathVariable Long id){
        return profissionalServices.deleteProfissional(id);
    }
}
