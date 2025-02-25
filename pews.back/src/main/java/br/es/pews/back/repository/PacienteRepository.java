package br.es.pews.back.repository;

import br.es.pews.back.models.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByNomePaciente(String nomePaciente);
    Optional<Paciente> findByCpfPaciente(String cpfPaciente);

    @Override
    Optional<Paciente> findById(Long id);
    @Override
    List<Paciente> findAll();

    Optional<Paciente> findPacienteByNomeResponsavel(String nomeResponsavel);
    Optional<Paciente> findPacienteByCpfResponsavel(String cpfResponsavel);
}
