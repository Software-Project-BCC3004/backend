package br.es.pews.back.models;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Documento {
    @NotBlank(message = "Número do documento não pode estar vazio")
    private String numeroDocumento;

    @NotBlank(message = "Tipo do documento não pode estar vazio")
    private String tipoDocumento;

    @NotBlank(message = "Estado do documento não pode estar vazio")
    private String estadoDocumento;
}
