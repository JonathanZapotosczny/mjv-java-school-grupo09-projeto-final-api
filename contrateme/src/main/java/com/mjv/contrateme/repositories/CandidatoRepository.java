package com.mjv.contrateme.repositories;

import com.mjv.contrateme.models.CadastroCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CandidatoRepository extends JpaRepository<CadastroCandidato, Integer> {
}
