package com.mjv.contrateme.repositories;

import com.mjv.contrateme.models.Profissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfissaoRepository extends JpaRepository<Profissao, Integer> {

}