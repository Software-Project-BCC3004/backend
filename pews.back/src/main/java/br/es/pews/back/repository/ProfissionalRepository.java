package br.es.pews.back.repository;

import br.es.pews.back.models.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Optional<Profissional> findByNomeProfissional(String nomeProfissional);
    @Override
    Optional<Profissional> findById(Long aLong);
    List<Profissional> findAll();

    Optional<Profissional> findByEmail(String email);

    Optional<Profissional> findByTipoDocumento(String tipo_documento);
    Optional<Profissional> findByNumeroDocumento(String numero_documento);
    Optional<Profissional> findByEstadoDocumento(String estado_documento);
}
