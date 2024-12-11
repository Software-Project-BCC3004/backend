package br.es.pews.back.dto;

import br.es.pews.back.models.Documento;
<<<<<<< HEAD
import br.es.pews.back.models.Paciente;

import java.util.List;
=======
>>>>>>> 66bb9f9adb94cefce061179282e8382bd5809914

public record ProfissionalDTO(
        Long id,
        Documento documento,
        String nome,
<<<<<<< HEAD
        String funcao,
        List<Paciente>pacientes
) {
}
=======
        String funcao
)
{ }
>>>>>>> 66bb9f9adb94cefce061179282e8382bd5809914
