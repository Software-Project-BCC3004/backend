package br.es.pews.back.dto;

import br.es.pews.back.models.Documento;
import br.es.pews.back.models.Paciente;

import java.util.List;

public record ProfissionalDTO(
        Long id,
        Documento documento,
        String nome,
        String funcao,
        List<Paciente>pacientes,
        String email,
        String senha
) {
}