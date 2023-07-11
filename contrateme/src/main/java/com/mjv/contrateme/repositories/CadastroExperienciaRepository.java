package com.mjv.contrateme.repositories;

import com.mjv.contrateme.models.CadastroExperiencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CadastroExperienciaRepository extends JpaRepository<CadastroExperiencia, Integer> {
}
