package br.es.pews.back.dto;


public record ProfissionalDTO(
        Long id,
        String nomeProfissional,
        String funcao,
        String email,
        String senha,
        String numeroDocumento,
        String tipoDocumento,
        String estadoDocumento

) {
}