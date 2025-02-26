package br.es.pews.back.services;


import br.es.pews.back.models.AvaliacaoPews;
import br.es.pews.back.repository.AvaliacaoPewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return repository.findAllByOrderByPontuacaoTotalDesc();
    }

    public Optional<AvaliacaoPews> findPontuacaoById(Long id) {
        return repository.findPontuacaoById(id);
    }


    public ResponseEntity<AvaliacaoPews> atualizarAvaliaacao (Long id, AvaliacaoPews avaliacao) {
        try {
            AvaliacaoPews avaliacaoPews = repository.findPontuacaoById(id)
                    .map(avaliacaoPews1 -> {
                        avaliacaoPews1.setAvaliacao_neurologica(avaliacao.getAvaliacao_neurologica());
                        avaliacaoPews1.setAvaliacao_cardiovascular(avaliacao.getAvaliacao_cardiovascular());
                        avaliacaoPews1.setAvaliacao_respiratoria(avaliacao.getAvaliacao_respiratoria());
                        avaliacaoPews1.setEmese(avaliacao.getEmese());
                        avaliacaoPews1.setNebulizacao(avaliacao.getNebulizacao());
                        avaliacaoPews1.setData_pews(avaliacao.getData_pews());

                        avaliacaoPews1.calcularPontuacao();

                        return repository.save(avaliacaoPews1);
                    }).orElseThrow(() -> new RuntimeException("Avalicação não encontrada"));

            return ResponseEntity.ok(avaliacaoPews);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<AvaliacaoPews> deletarAvaliaacao(Long id) {
        try {
            AvaliacaoPews avaliacaoPews = repository.findPontuacaoById(id)
                    .orElseThrow(() -> new RuntimeException("Avaliação não encontrada"));

            repository.delete(avaliacaoPews);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
