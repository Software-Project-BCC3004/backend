package br.es.pews.back.controller;

import br.es.pews.back.dto.PacienteDTO;
import br.es.pews.back.models.Paciente;
import br.es.pews.back.models.Profissional;
import br.es.pews.back.models.Responsavel;
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

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        return pacienteServices.getPacienteById(id);
    }

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        return pacienteServices.getAllPacientes();
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Paciente> getPacienteByCPF(@PathVariable String cpf) {
        return pacienteServices.getPacienteByCPF(cpf);
    }


    @GetMapping("/nome/{nome}")
    public ResponseEntity<Paciente> getPacienteByNome(@PathVariable String nome) {
        return pacienteServices.getPacienteByNome(nome);
    }

    @GetMapping("/responsavel")
    public ResponseEntity<List<Paciente>> getPacienteByNomeResponsavel(@RequestBody Responsavel responsavel) {
        return pacienteServices.getPacienteByNomeResponsavel(responsavel);
    }

    @GetMapping("/profissional")
    public ResponseEntity<List<Paciente>> getPacienteByProfissionalOrderByNome(@RequestBody Profissional profissional) {
        return pacienteServices.getPacienteByProfissionalOrderByNome(profissional);
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        return pacienteServices.createPaciente(paciente);
    }

    @PutMapping("/paciente/{id}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable Long id, @RequestBody PacienteDTO pacienteDTO) {
        return pacienteServices.updatePaciente(id, pacienteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Paciente> deletePaciente(@PathVariable Long id) {
        return pacienteServices.deletePaciente(id);
    }
}
