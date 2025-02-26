package br.es.pews.back.models;

import br.es.pews.back.form.Pontuacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "avaliacao_pews")
public class AvaliacaoPews {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Pontuacao avaliacao_neurologica;

    @Enumerated(EnumType.STRING)
    private Pontuacao avaliacao_cardiovascular;

    @Enumerated(EnumType.STRING)
    private Pontuacao avaliacao_respiratoria;

    @Enumerated(EnumType.STRING)
    private Pontuacao emese;

    @Enumerated(EnumType.STRING)
    private Pontuacao nebulizacao;

    private int pontuacaoTotal;

    @Column(name = "data_pews", nullable = false)
    private LocalDateTime data_pews;


    @PrePersist
    @PreUpdate
    public void calcularPontuacao() {
        this.pontuacaoTotal =
                (avaliacao_neurologica != null ? avaliacao_neurologica.getScore() : 0) +
                        (avaliacao_cardiovascular != null ? avaliacao_cardiovascular.getScore() : 0) +
                        (avaliacao_respiratoria != null ? avaliacao_respiratoria.getScore() : 0) +
                        (emese != null ? emese.getScore() : 0) +
                        (nebulizacao != null ? nebulizacao.getScore() : 0);
    }

}
