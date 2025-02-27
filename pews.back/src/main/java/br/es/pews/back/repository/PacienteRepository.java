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
    @Query("SELECT p FROM Paciente p WHERE LOWER(p.nomePaciente) LIKE LOWER(CONCAT(:nomePaciente, '%'))")
    List<Paciente> findByNomePaciente(@Param("nomePaciente")String nomePaciente);
    List<Paciente> findByCpfPaciente(String cpfPaciente);

    @Override
    Optional<Paciente> findById(Long id);
    @Override
    List<Paciente> findAll();

    @Query("SELECT p FROM Paciente p WHERE LOWER(p.nomeResponsavel) LIKE LOWER(CONCAT(:nomeResponsavel, '%'))")
    List<Paciente> findByNomeResponsavel(@Param("nomeResponsavel")String nomeResponsavel);
    List<Paciente> findByCpfResponsavel(String cpfResponsavel);
}
