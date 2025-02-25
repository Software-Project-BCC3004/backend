package br.es.pews.back.models;

import br.es.pews.back.dto.ProfissionalDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;


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

    @NotBlank(message = "Número do documento não pode estar vazio")
    @Column(name = "numeroDocumento", nullable = false)
    private String numeroDocumento;

    @NotBlank(message = "Tipo do documento não pode estar vazio")
    @Column(name = "tipoDocumento", nullable = false)
    private String tipoDocumento;

    @NotBlank(message = "Estado do documento não pode estar vazio")
    @Column(name = "estadoDocumento", nullable = false)
    private String estadoDocumento;

    public Profissional (ProfissionalDTO profissionalDTO) {
        this.id = profissionalDTO.id();
        this.numeroDocumento = profissionalDTO.numeroDocumento();
        this.tipoDocumento = profissionalDTO.tipoDocumento();
        this.estadoDocumento = profissionalDTO.estadoDocumento();
        this.nomeProfissional = profissionalDTO.nomeProfissional();
        this.funcao = profissionalDTO.funcao();
        this.emailprofissional = profissionalDTO.emailprofissional();
        this.senhaProfissional = profissionalDTO.senhaProfissional();
    }
}