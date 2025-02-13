package br.es.pews.back.services;


import br.es.pews.back.form.AvaliacaoPews;
import br.es.pews.back.repository.AvaliacaoPewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AvaliacaoPewsServices {

    @Autowired
    private AvaliacaoPewsRepository repository;

    public AvaliacaoPews salvar(AvaliacaoPews avaliacao) {
        avaliacao.calcularPontuacao();
        return repository.save(avaliacao);
    }

    public List<AvaliacaoPews> listarPorPontuacao() {
        return repository.getPontuacaoByOrderByPontucaoTotalDesc();
    }

    public Optional<AvaliacaoPews> findPontuacaoById(Long id) {
        return repository.findPontuacaoById(id);
    }
}