package br.es.pews.back.dto;


public record PacienteDTO(
        Long id,
        String nomePaciente,
        String cpfPaciente,
        String diagnostico,
        String leito,
        String nomeResponsavel,
        String cpfResponsavel,
        String grauSeveridade
        ) {}