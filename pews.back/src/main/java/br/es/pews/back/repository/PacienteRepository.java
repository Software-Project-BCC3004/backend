package br.es.pews.back.repository;

import br.es.pews.back.models.Paciente;
import br.es.pews.back.models.Profissional;
import br.es.pews.back.models.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByNome(String nome);
    Optional<Paciente> findByCpfPaciente(String cpfPaciente);
    List<Paciente> findByResponsavel(Responsavel responsavel);
    List<Paciente> findByProfissionalOrderByNome(Profissional profissional);
    @Override
    Optional<Paciente> findById(Long id);
    @Override
    List<Paciente> findAll();
}
