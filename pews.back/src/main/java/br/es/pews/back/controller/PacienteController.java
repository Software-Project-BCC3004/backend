package br.es.pews.back.controller;

import br.es.pews.back.dto.PacienteDTO;
import br.es.pews.back.models.Paciente;
import br.es.pews.back.services.PacienteServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteServices pacienteServices;

    @GetMapping("/consultar/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteServices.getPacienteById(id);
    }

    @GetMapping("/consultar/todos")
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        return pacienteServices.getAllPacientes();
    }

    @GetMapping("/consultar/cpf/{cpfPaciente}")
    public ResponseEntity<List<Paciente>>  getPacienteByCPF(@PathVariable String cpfPaciente) {
        return pacienteServices.getPacienteByCPF(cpfPaciente);
    }

    @GetMapping("/consultar/nome/{nomePaciente}")
    public ResponseEntity<List<Paciente>>  getPacienteByNome(@PathVariable String nomePaciente) {
        return pacienteServices.getPacienteByNome(nomePaciente);
    }

    @GetMapping("/consultar/responsavel/nome/{nomeResponsavel}")
    public ResponseEntity<List<Paciente>>  getPacienteByNomeResponsavel(@PathVariable String nomeResponsavel) {
        return pacienteServices.getPacienteByNomeResponsavel(nomeResponsavel);
    }

    @GetMapping("/consultar/responsavel/cpf/{cpfResponsavel}")
    public ResponseEntity<List<Paciente>>  getPacienteByCpfResponsavel(@PathVariable String cpfResponsavel) {
        return pacienteServices.getPacienteByCpfResponsavel(cpfResponsavel);
    }

    @PostMapping("/criar")
    public ResponseEntity<Paciente> createPaciente(@RequestBody PacienteDTO pacienteDTO) {
        return pacienteServices.createPaciente(pacienteDTO);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        return pacienteServices.updatePaciente(id, pacienteDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletePaciente(@PathVariable Long id) {
        return pacienteServices.deletePaciente(id);
    }

}
