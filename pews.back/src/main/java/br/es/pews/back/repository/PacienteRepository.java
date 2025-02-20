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
    Optional<Paciente> findByNome(String nome);
    Optional<Paciente> findByCpfPaciente(String cpfPaciente);

    @Query("SELECT p FROM Paciente p WHERE p.responsavel.nome =:nome")
    List<Paciente> findByNomeResponsavel(@Param("nome") String nome);

    @Query("SELECT p FROM Paciente p WHERE LOWER(p.profissional.nome) LIKE LOWER(CONCAT('%', :nome, '%')) ORDER BY p.nome ASC")
    List<Paciente> findByProfissionalOrderByNome(@Param("nome") String nome);

    @Override
    Optional<Paciente> findById(Long id);
    @Override
    List<Paciente> findAll();
}
