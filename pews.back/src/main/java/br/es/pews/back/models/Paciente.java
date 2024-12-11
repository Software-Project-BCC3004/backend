package br.es.pews.back.models;

import br.es.pews.back.dto.PacienteDTO;
import jakarta.persistence.*;
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
    private String nome;
    private String cpfPaciente;
    private String diagnostico;
    private String leito;

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
    }
}
