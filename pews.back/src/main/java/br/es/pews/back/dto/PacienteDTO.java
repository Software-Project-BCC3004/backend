package br.es.pews.back.dto;

import br.es.pews.back.models.Profissional;
import br.es.pews.back.models.Responsavel;

public record PacienteDTO(
        Long id,
        String nome,
        String cpfPaciente,
        String diagnostico,
        String leito,
        Responsavel responsavel,
        Profissional profissional

        ) {
}
