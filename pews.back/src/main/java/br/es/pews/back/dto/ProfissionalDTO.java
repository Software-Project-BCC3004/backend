package br.es.pews.back.dto;

import br.es.pews.back.models.Documento;

public record ProfissionalDTO(
        Long id,
        Documento documento,
        String nome,
        String funcao
)
{ }
