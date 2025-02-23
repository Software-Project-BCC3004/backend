package br.es.pews.back.dto;

import br.es.pews.back.models.Documento;
import br.es.pews.back.models.Paciente;

import java.util.List;

public record ProfissionalDTO(
        Long id,
        Documento documento,
        String nomeProfissional,
        String funcao,
        List<Paciente>pacientes,
        String emailprofissional,
        String senhaProfissional
) {
}