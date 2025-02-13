package br.es.pews.back.repository;

import br.es.pews.back.form.AvaliacaoPews;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvaliacaoPewsRepository extends JpaRepository<AvaliacaoPews, Long> {
    Optional<AvaliacaoPews> findPontuacaoById(Long id);

    List<AvaliacaoPews> getPontuacaoByOrderByPontucaoTotalDesc();
}
