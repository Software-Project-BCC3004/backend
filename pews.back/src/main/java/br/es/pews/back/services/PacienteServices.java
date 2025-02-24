package br.es.pews.back.services;

import br.es.pews.back.dto.PacienteDTO;
import br.es.pews.back.models.Paciente;
import br.es.pews.back.models.Profissional;
import br.es.pews.back.models.Responsavel;
import br.es.pews.back.repository.PacienteRepository;
import br.es.pews.back.repository.ProfissionalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public ResponseEntity<Paciente> getPacienteByCPF(String cpf) {
        return pacienteRepository.findByCpfPaciente(cpf)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Paciente> getPacienteByNome(String nomePaciente) {
        return pacienteRepository.findByNomePaciente(nomePaciente)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<List<Paciente>> getPacienteByNomeResponsavel(String nome) {
        List<Paciente> pacienteResponsavel = pacienteRepository.findByNomeResponsavel(nome);
        return pacienteResponsavel.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(pacienteResponsavel);
    }

    public ResponseEntity<List<Paciente>> getPacienteByProfissionalOrderByNome(String nome) {
        List<Paciente> pacienteProfissional = pacienteRepository.findByProfissionalOrderByNome(nome);
        return pacienteProfissional.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(pacienteProfissional);
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

            if (pacienteDTO.responsavel() != null) {
                paciente.setResponsavel(new Responsavel(
                        pacienteDTO.responsavel().getNomeResponsavel(),
                        pacienteDTO.responsavel().getCpfResponsavel()
                ));
            }

            if (pacienteDTO.profissional() != null && pacienteDTO.profissional().getId() != null) {
                Optional<Profissional> profissional = profissionalRepository.findById(pacienteDTO.profissional().getId());
                profissional.ifPresent(paciente::setProfissional);
            }

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
            if (pacienteDTO.responsavel() != null) {
                if (pacienteUpdate.getResponsavel() == null) {
                    pacienteUpdate.setResponsavel(new Responsavel());
                }
                pacienteUpdate.getResponsavel().setNomeResponsavel(pacienteDTO.responsavel().getNomeResponsavel());
                pacienteUpdate.getResponsavel().setCpfResponsavel(pacienteDTO.responsavel().getCpfResponsavel());
            }

            if (pacienteDTO.profissional() != null && pacienteDTO.profissional().getId() != null) {
                Optional<Profissional> profissional = profissionalRepository.findById(pacienteDTO.profissional().getId());
                profissional.ifPresent(pacienteUpdate::setProfissional);
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