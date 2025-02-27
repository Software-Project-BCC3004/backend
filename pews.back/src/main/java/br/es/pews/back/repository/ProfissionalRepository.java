package br.es.pews.back.repository;

import br.es.pews.back.models.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    @Query("SELECT p FROM Profissional p WHERE LOWER(p.nomeProfissional) LIKE LOWER(CONCAT(:nomeProfissional, '%'))")
    Optional<Profissional> findByNomeProfissional(String nomeProfissional);
    @Override
    Optional<Profissional> findById(Long aLong);
    List<Profissional> findAll();

    Optional<Profissional> findByEmail(String email);

    List<Profissional> findByTipoDocumento(String tipoDocumento);
    Optional<Profissional> findByNumeroDocumento(String numeroDocumento);
    Optional<Profissional> findByEstadoDocumento(String estado_documento);
}
