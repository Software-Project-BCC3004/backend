package br.es.pews.back.models;

import br.es.pews.back.dto.PacienteDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode estar vazio")
    @Column(name = "nomePaciente", nullable = false)
    private String nomePaciente;
    @NotBlank(message = "CPF do paciente não pode estar vazio")
    @Column(name = "cpfPaciente", unique = true, nullable = false)
    @CPF(message = "CPF inválido")
    private String cpfPaciente;

    @NotBlank(message = "Diagnóstico do paciente não pode estar vazio")
    @Column(name = "diagnostico", nullable = false)
    private String diagnostico;

    @NotBlank(message = "Leito do paciente não pode estar vazio")
    @Column(name = "leito", nullable = false)
    private String leito;

    @Column(name = "grauSeveridade", nullable = false)
    private String grauSeveridade;

    @Column(name = "nomeResponsavel", nullable = false)
    private String nomeResponsavel;

    @CPF(message = "CPF inválido")
    @Column(name = "cpfResponsavel", nullable = false)
    private String cpfResponsavel;
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medico_id", nullable = false)
    @JsonIgnore
    private Profissional profissional;

    public Paciente(PacienteDTO pacienteDTO) {
        if (pacienteDTO != null) {
            this.id = pacienteDTO.id();
            this.nomePaciente = pacienteDTO.nomePaciente();
            this.cpfPaciente = pacienteDTO.cpfPaciente();
            this.diagnostico = pacienteDTO.diagnostico();
            this.leito = pacienteDTO.leito();
            this.grauSeveridade = pacienteDTO.grauSeveridade();
            this.nomeResponsavel = pacienteDTO.nomeResponsavel();
            this.cpfResponsavel = pacienteDTO.cpfResponsavel();
        }
    }
}
