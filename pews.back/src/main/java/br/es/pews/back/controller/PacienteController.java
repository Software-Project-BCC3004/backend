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

    @GetMapping("/consultar/cpf/{cpf}")
    public ResponseEntity<Paciente> getPacienteByCPF(@PathVariable String cpf) {
        return pacienteServices.getPacienteByCPF(cpf);
    }

    @GetMapping("/consultar/nome/{nome}")
    public ResponseEntity<Paciente> getPacienteByNome(@PathVariable String nome) {
        return pacienteServices.getPacienteByNome(nome);
    }

    @GetMapping("/consultar/responsavel/nome/{nomeResponsavel}")
    public ResponseEntity<Paciente> getPacienteByNomeResponsavel(@PathVariable String nome_responsavel) {
        return pacienteServices.getPacienteByNomeResponsavel(nome_responsavel);
    }

    @GetMapping("/consultar/responsavel/nome/{cpfResponsavel}")
    public ResponseEntity<Paciente> getPPacienteByCpfResponsavel(@PathVariable String cpf_responsavel) {
        return pacienteServices.getPPacienteByCpfResponsavel(cpf_responsavel);
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
