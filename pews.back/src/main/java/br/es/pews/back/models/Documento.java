package br.es.pews.back.models;

import jakarta.persistence.Embeddable;

@Embeddable
public record Documento(
        String tipoDocumento,
        String numerosDocumento,
        String estadoDocumento
    )
{ }
