package br.es.pews.back.models;

import jakarta.persistence.Embeddable;

@Embeddable
public record Responsavel(
        String nome,
        String cpf
)
{ }
