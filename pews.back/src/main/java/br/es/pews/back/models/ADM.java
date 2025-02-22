package br.es.pews.back.models;

import br.es.pews.back.dto.AdmDTO;
import br.es.pews.back.dto.PacienteDTO;
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
    @Column(unique = true, nullable = false)
    private String email;
    @Column(unique = true, nullable = false)
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public ADM(AdmDTO admDTO){
        this.id = admDTO.id();
        this.email = admDTO.email();
        this.password = admDTO.senha();
        
    }
}

