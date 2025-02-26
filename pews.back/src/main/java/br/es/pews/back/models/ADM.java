package br.es.pews.back.models;

import br.es.pews.back.dto.AdmDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 
@Entity
@Table(name = "admin")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ADM {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email_adm", unique = true, nullable = false)
    private String email;
    @Column(name = "senha_adm", nullable = false)
    private String senha;

    public void setSenha_adm(String senha_adm) {
        this.senha = senha_adm;
    }

    public String getSenha_adm() {
        return senha;
    }

    public ADM(AdmDTO admDTO) {
        this.email = admDTO.email();
        this.senha = admDTO.senha();
    }
}

