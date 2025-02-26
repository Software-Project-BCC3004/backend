package br.es.pews.back.services;

import br.es.pews.back.dto.PacienteDTO;
import br.es.pews.back.models.Paciente;
import br.es.pews.back.repository.PacienteRepository;
import br.es.pews.back.repository.ProfissionalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServices {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public ResponseEntity<Paciente> getPacienteById(Long id) {
        return pacienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        return pacientes.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(pacientes);
    }

    public ResponseEntity<Paciente> getPacienteByCPF(String cpfPaciente) {
        return pacienteRepository.findByCpfPaciente(cpfPaciente)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Paciente> getPacienteByNome(String nomePaciente) {
        return pacienteRepository.findByNomePaciente(nomePaciente)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Paciente> getPacienteByNomeResponsavel (String nomeResponsavel) {
        return pacienteRepository.findByNomeResponsavel(nomeResponsavel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Paciente> getPacienteByCpfResponsavel (String cpfResponsavel) {
        return pacienteRepository.findByCpfResponsavel(cpfResponsavel)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<Paciente> createPaciente(PacienteDTO pacienteDTO) {
        try {
            System.out.println("📌 Criando paciente: " + pacienteDTO);

            Paciente paciente = new Paciente();
            paciente.setNomePaciente(pacienteDTO.nomePaciente());
            paciente.setCpfPaciente(pacienteDTO.cpfPaciente());
            paciente.setDiagnostico(pacienteDTO.diagnostico());
            paciente.setLeito(pacienteDTO.leito());
            paciente.setGrauSeveridade(pacienteDTO.grauSeveridade());
            paciente.setNomeResponsavel(pacienteDTO.nomeResponsavel());
            paciente.setCpfResponsavel(pacienteDTO.cpfResponsavel());

            Paciente pacienteSalvo = pacienteRepository.saveAndFlush(paciente);
            System.out.println("✅ Paciente salvo: " + pacienteSalvo);

            return ResponseEntity.ok(pacienteSalvo);
        } catch (Exception e) {
            System.out.println("❌ ERRO AO SALVAR PACIENTE: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Transactional
    public ResponseEntity<Paciente> updatePaciente(Long id, PacienteDTO pacienteDTO) {
        try {
            System.out.println("📌 Atualizando paciente com ID: " + id);

            Paciente pacienteUpdate = pacienteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("❌ Paciente com ID " + id + " não encontrado"));

            if (pacienteDTO.nomePaciente() != null && !pacienteDTO.nomePaciente().isBlank()) {
                pacienteUpdate.setNomePaciente(pacienteDTO.nomePaciente());
            }
            if (pacienteDTO.cpfPaciente() != null && !pacienteDTO.cpfPaciente().isBlank()) {
                pacienteUpdate.setCpfPaciente(pacienteDTO.cpfPaciente());
            }
            if (pacienteDTO.diagnostico() != null && !pacienteDTO.diagnostico().isBlank()) {
                pacienteUpdate.setDiagnostico(pacienteDTO.diagnostico());
            }
            if (pacienteDTO.leito() != null && !pacienteDTO.leito().isBlank()) {
                pacienteUpdate.setLeito(pacienteDTO.leito());
            }
            if (pacienteDTO.grauSeveridade() != null && !pacienteDTO.grauSeveridade().isBlank()) {
                pacienteUpdate.setGrauSeveridade(pacienteDTO.grauSeveridade());
            }

            if (pacienteDTO.nomeResponsavel() != null && !pacienteDTO.nomeResponsavel().isBlank()) {
                pacienteUpdate.setNomeResponsavel(pacienteDTO.nomeResponsavel());
            }

            if (pacienteDTO.cpfResponsavel() != null && !pacienteDTO.cpfResponsavel().isBlank()) {
                pacienteUpdate.setCpfResponsavel(pacienteDTO.cpfResponsavel());
            }

            Paciente pacienteAtualizado = pacienteRepository.saveAndFlush(pacienteUpdate);
            System.out.println("✅ Paciente atualizado com sucesso: " + pacienteAtualizado);

            return ResponseEntity.ok(pacienteAtualizado);
        } catch (Exception e) {
            System.out.println("❌ ERRO AO ATUALIZAR PACIENTE: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @Transactional
    public ResponseEntity<Void> deletePaciente(Long id) {
        try {
            System.out.println("📌 Tentando deletar paciente com ID: " + id);
            Paciente pacienteDelete = pacienteRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("❌ Paciente com ID " + id + " não encontrado"));

            pacienteRepository.delete(pacienteDelete);
            System.out.println("✅ Paciente deletado com sucesso!");

            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            System.out.println("❌ ERRO AO DELETAR PACIENTE: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}