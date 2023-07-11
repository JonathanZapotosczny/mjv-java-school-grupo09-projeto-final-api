package com.mjv.contrateme.repositories;

import com.mjv.contrateme.models.CadastroExperiencia;
import com.mjv.contrateme.models.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabilidadeRepository extends JpaRepository<Habilidade, Integer> {
}
