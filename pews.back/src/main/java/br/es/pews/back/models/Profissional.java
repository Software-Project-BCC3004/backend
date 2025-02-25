package br.es.pews.back.models;

import br.es.pews.back.dto.ProfissionalDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Documento documento;
    @NotBlank(message = "Nome não pode estar vazio")
    @Column(name = "nomeProfissional", unique = true, nullable = false)
    private String nomeProfissional;
    @NotBlank(message = "Função não pode estar vazio")
    @Column(name = "funcaoProfissional", nullable = false)
    private String funcao;
    @Email(message = "Email não válido")
    @NotBlank(message = "Email não pode estar vazio")
    @Column(name = "emailprofissional", unique = true, nullable = false)
    private String emailprofissional;
    @NotBlank(message = "Senha não pode estar vazio")
    @Column(name = "senhaProfissional", nullable = false)
    private String senhaProfissional;

    @OneToMany(mappedBy = "profissional", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Paciente> pacientes;

    public Profissional (ProfissionalDTO profissionalDTO) {
        this.id = profissionalDTO.id();
        this.documento = profissionalDTO.documento();
        this.nomeProfissional = profissionalDTO.nomeProfissional();
        this.funcao = profissionalDTO.funcao();
        this.emailprofissional = profissionalDTO.emailprofissional();
        this.senhaProfissional = profissionalDTO.senhaProfissional();
    }
}