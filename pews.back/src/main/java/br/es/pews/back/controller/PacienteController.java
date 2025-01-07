package br.es.pews.back.controller;

import br.es.pews.back.models.Paciente;
import br.es.pews.back.services.PacienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PacienteController {

    @Autowired
    private PacienteServices pacienteServices;

    @GetMapping
    public ResponseEntity<Paciente> getPacienteById(Long id) {
        return pacienteServices.getPacienteById(id);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        return pacienteServices.getAllPacientes();
    }

    @GetMapping
    public ResponseEntity<Paciente> getPacienteByCPF(String cpf) {
        return pacienteServices.getPacienteByCPF(cpf);
    }
}
