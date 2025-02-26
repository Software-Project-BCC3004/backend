package br.es.pews.back.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AdmDTO(
        Long id,
        @JsonProperty("email_adm") String email,
        @JsonProperty("senha_adm") String senha
) {}
