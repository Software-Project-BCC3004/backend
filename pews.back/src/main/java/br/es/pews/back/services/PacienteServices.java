package br.es.pews.back.services;

import br.es.pews.back.models.Paciente;
import br.es.pews.back.repository.PacienteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServices {

    @Autowired
    private PacienteRepository pacienteRepository;

    public ResponseEntity<Paciente> getPacienteById(Long id) {
        Optional<Paciente> paciente = pacienteRepository.findById(id);
        return paciente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        if (pacientes.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(pacientes);
    }

    public ResponseEntity<Paciente> getPacienteByCPF(String cpf) {
       Optional<Paciente> pacienteCPF = pacienteRepository.findByCpfPaciente(cpf);
       return pacienteCPF.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        try {
            Paciente pacienteSalvo = pacienteRepository.save(paciente);
            return ResponseEntity.ok().body(pacienteSalvo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
