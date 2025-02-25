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
    private Pontuacao avaliacaoNeurologica;

    @Enumerated(EnumType.STRING)
    private Pontuacao avaliacaoCardiovascular;

    @Enumerated(EnumType.STRING)
    private Pontuacao avaliacaoRespiratoria;

    @Enumerated(EnumType.STRING)
    private Pontuacao emese;

    @Enumerated(EnumType.STRING)
    private Pontuacao nebulizacao;

    private int pontuacaoTotal;

    @Column(name = "dataPews", nullable = false)
    private LocalDateTime dataPews;


    @PrePersist
    @PreUpdate
    public void calcularPontuacao() {
        this.pontuacaoTotal =
                (avaliacaoNeurologica != null ? avaliacaoNeurologica.getScore() : 0) +
                        (avaliacaoCardiovascular != null ? avaliacaoCardiovascular.getScore() : 0) +
                        (avaliacaoRespiratoria != null ? avaliacaoRespiratoria.getScore() : 0) +
                        (emese != null ? emese.getScore() : 0) +
                        (nebulizacao != null ? nebulizacao.getScore() : 0);
    }

}
