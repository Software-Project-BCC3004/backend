package br.es.pews.back.models;

import br.es.pews.back.dto.ProfissionalDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @Column(name = "nome_profissional", unique = true, nullable = false)
    private String nomeProfissional;

    @NotBlank(message = "Função não pode estar vazia")
    @Column(name = "funcao_profissional", nullable = false)
    private String funcao;

    @Email(message = "Email não é válido")
    @NotBlank(message = "Email não pode estar vazio")
    @Column(name = "email_profissional", unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Senha não pode estar vazia")
    @Column(name = "senha_profissional", nullable = false)
    private String senha;

    @NotBlank(message = "Número do documento não pode estar vazio")
    @Column(name = "numero_documento", nullable = false)
    private String numeroDocumento;

    @NotBlank(message = "Tipo do documento não pode estar vazio")
    @Column(name = "tipo_documento", nullable = false)
    private String tipoDocumento;

    @NotBlank(message = "Estado do documento não pode estar vazio")
    @Column(name = "estado_documento", nullable = false)
    private String estadoDocumento;

    public void setSenha_profissional(String senha_profissional) {
        this.senha = senha_profissional;
    }

    public String getSenha_profissional() {
        return senha;
    }

    public Profissional(ProfissionalDTO profissionalDTO) {
        this.nomeProfissional = profissionalDTO.nomeProfissional();
        this.tipoDocumento = profissionalDTO.tipoDocumento();
        this.estadoDocumento = profissionalDTO.estadoDocumento();
        this.numeroDocumento = profissionalDTO.numeroDocumento();
        this.funcao = profissionalDTO.funcao();
        this.email = profissionalDTO.email();
        this.senha = profissionalDTO.senha();
    }
}
