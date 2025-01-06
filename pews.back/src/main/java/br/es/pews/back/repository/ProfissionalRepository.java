package br.es.pews.back.repository;

import br.es.pews.back.models.Documento;
import br.es.pews.back.models.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Optional<Profissional> findByNome(String nome);
    Optional<Profissional> findProfissionalByDocumento (Documento documento);
    @Override
    Optional<Profissional> findById(Long aLong);
    List<Profissional> findAll();
}
