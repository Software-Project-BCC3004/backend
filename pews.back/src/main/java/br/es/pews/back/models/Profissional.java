package br.es.pews.back.models;

import br.es.pews.back.dto.ProfissionalDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

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
    @NotBlank(message = "Documento não pode estar vazio")
    @Column(unique = true, nullable = false)
    private Documento documento;
    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;
    @NotBlank(message = "Função não pode estar vazio")
    private String funcao;
    @Email(message = "Email não válido")
    @NotBlank(message = "Email não pode estar vazio")
    private String email;

    @OneToMany(mappedBy = "profissional")
    private List<Paciente> pacientes;

    public Profissional (ProfissionalDTO profissionalDTO) {
        this.id = profissionalDTO.id();
        this.documento = profissionalDTO.documento();
        this.nome = profissionalDTO.nome();
        this.funcao = profissionalDTO.funcao();
        this.email = profissionalDTO.email();
    }
}
