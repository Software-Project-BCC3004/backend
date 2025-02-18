package br.es.pews.back.repository;

import br.es.pews.back.models.ADM;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ADMRepository extends JpaRepository<ADM, Long> { }
