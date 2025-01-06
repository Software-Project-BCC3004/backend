package br.es.pews.back.controller;

import br.es.pews.back.models.Profissional;
import br.es.pews.back.services.ProfissionalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProfissionalController {

    @Autowired
    private ProfissionalServices profissionalServices;

    @GetMapping
    public ResponseEntity<Profissional> getProfissionalById(Long id) {
        return profissionalServices.getProfissionalById(id);
    }
}
