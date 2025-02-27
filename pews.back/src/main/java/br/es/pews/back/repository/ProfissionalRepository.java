package br.es.pews.back.repository;

import br.es.pews.back.models.Profissional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfissionalRepository extends JpaRepository<Profissional, Long> {
    @Query("SELECT p FROM Profissional p WHERE LOWER(p.nomeProfissional) LIKE LOWER(CONCAT(:nomeProfissional, '%'))")
    Optional<Profissional> findByNomeProfissional(String nomeProfissional);

    @Query("SELECT p FROM Profissional p WHERE p.nomeProfissional ILIKE :nome")
    Optional<Profissional> findByNomeProfissional(@Param("nome") String nomeProfissional);


    Optional<Profissional> findProfissionalByDocumento (Documento documento);

    @Override
    Optional<Profissional> findById(Long aLong);
    List<Profissional> findAll();

    Optional<Profissional> findByEmail(String email);

    List<Profissional> findByTipoDocumento(String tipoDocumento);
    Optional<Profissional> findByNumeroDocumento(String numeroDocumento);
    Optional<Profissional> findByEstadoDocumento(String estado_documento);

    Optional<Profissional> findProfissionalBytipoDocumentoProfissional(String tipoDocumentoProfissional);
    Optional<Profissional> findProfissionalByNumeroDocumentoProfissional(String numeroDocumentoProfissional);
    Optional<Profissional> findProfissionalByEstadoDocumentoProfissional(String estadoDocumentoProfissional);

    
    @Query("SELECT p FROM Profissional p WHERE LOWER(p.nomeProfissional) LIKE LOWER(CONCAT(:nome, '%'))")
    List<Profissional> findByPrimeiroNome(@Param("nome") String primeiroNome);

    



    
}
