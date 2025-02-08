package br.es.pews.back.models;

import br.es.pews.back.dto.PacienteDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "paciente")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;
    @NotBlank(message = "Nome não pode estar vazio")
    private String nome;
    @NotBlank(message = "CPF do paciente não pode estar vazio")
    @Column(unique = true, nullable = false)
    private String cpfPaciente;
    @NotBlank(message = "Diagnóstico do paciente não pode estar vazio")
    private String diagnostico;
    @NotBlank(message = "Leito do paciente não pode estar vazio")
    private String leito;

    private String grauSeveridade;

    @Embedded
    private Responsavel responsavel;

    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Profissional profissional;

    public Paciente(PacienteDTO pacienteDTO){
        this.id = pacienteDTO.id();
        this.nome = pacienteDTO.nome();
        this.cpfPaciente = pacienteDTO.cpfPaciente();
        this.diagnostico = pacienteDTO.diagnostico();
        this.leito = pacienteDTO.leito();
        this.responsavel = pacienteDTO.responsavel();
        this.profissional = pacienteDTO.profissional();
        this.grauSeveridade = pacienteDTO.grauSeveridade();
    }
}
