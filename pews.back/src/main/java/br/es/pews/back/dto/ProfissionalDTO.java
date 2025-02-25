package br.es.pews.back.dto;


public record ProfissionalDTO(
        Long id,
        String nomeProfissional,
        String funcao,
        String emailprofissional,
        String senhaProfissional,
        String numeroDocumento,
        String tipoDocumento,
        String estadoDocumento

) {
}