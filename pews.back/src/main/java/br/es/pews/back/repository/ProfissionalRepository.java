package br.es.pews.back.repository;

import br.es.pews.back.models.Documento;
import br.es.pews.back.models.Paciente;
import br.es.pews.back.models.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    Profissional findByNome(String nome);
    Profissional findProfissionalByDocumento (Documento documento);
}
