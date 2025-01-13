package br.es.pews.back.models;

import br.es.pews.back.dto.ProfissionalDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profissional")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Profissional {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    @Embedded
    private Documento documento;
    private String nome;
    private String funcao;

    @OneToMany(mappedBy = "profissional")
    private List<Paciente> pacientes;

    public Profissional (ProfissionalDTO profissionalDTO) {
        this.id = profissionalDTO.id();
        this.documento = profissionalDTO.documento();
        this.nome = profissionalDTO.nome();
        this.funcao = profissionalDTO.funcao();
    }
}
