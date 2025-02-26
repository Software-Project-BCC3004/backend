package br.es.pews.back.controller;


import br.es.pews.back.models.AvaliacaoPews;
import br.es.pews.back.services.AvaliacaoPewsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/avaliacao/pews")
public class AvaliacaoPewsController {

    @Autowired
    private AvaliacaoPewsServices services;


    @PostMapping("/criar")
    public ResponseEntity<AvaliacaoPews> criar(@RequestBody AvaliacaoPews avaliacaoPews) {
        return ResponseEntity.ok(services.salvar(avaliacaoPews));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<AvaliacaoPews>> listar() {
        return ResponseEntity.ok(services.listarPorPontuacao());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvaliacaoPews> buscarPorId(@PathVariable Long id) {
        return services.findPontuacaoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AvaliacaoPews> atualizar (@PathVariable Long id, @RequestBody AvaliacaoPews avaliacaoPews) {
        return services.atualizarAvaliaacao(id, avaliacaoPews);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<AvaliacaoPews> deletar(@PathVariable Long id) {
        return services.deletarAvaliaacao(id);
    }
}
