package com.mjv.contrateme.repositories;

import com.mjv.contrateme.models.CadastroCandidato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CandidatoRepository extends JpaRepository<CadastroCandidato, Integer> {

    @Query("SELECT COUNT(c) FROM CadastroCandidato c JOIN c.habilidades h WHERE h.nome = :nome")
    Integer contarCandidatosComHabilidade(@Param("nome") String nome);

    @Query("SELECT c FROM CadastroCandidato c WHERE NOT EXISTS (SELECT h FROM c.habilidades h WHERE h.nome = :nome)")
    List<CadastroCandidato> buscarCandidatosSemHabilidade(@Param("nome") String nome);

    //@Query("SELECT c FROM CadastroCandidato c WHERE NOT EXISTS (SELECT h FROM c.habilidades h WHERE h.nome = :nome)")
    //List<CadastroCandidato> buscarCandidatosSemHabilidade();

    @Query("SELECT CONCAT(p.nome, ' - ', COUNT(*)) FROM CadastroCandidato c JOIN c.profissao p " +
            "JOIN c.endereco.cidade ci WHERE ci.nome = :nome GROUP BY p.nome")
    List<String> quantidadeProfissionaisPorCidade(@Param("nome") String nome);

    @Query("SELECT c, e.dataContratacao, e FROM CadastroCandidato c JOIN c.experiencias e " +
            "WHERE e.dataContratacao BETWEEN :dataInicio AND :dataFim")
    List<CadastroCandidato> candidatosComExperienciaPorData(@Param ("dataInicio") LocalDate dataInicio,
                                                            @Param ("dataFim") LocalDate dataFim);


    @Query("SELECT c FROM CadastroCandidato c JOIN FETCH c.experiencias e WHERE e.empresa = :nome")
    List<CadastroCandidato> candidatoPorExperiencia(@Param("nome") String nome);

    @Query("SELECT c FROM CadastroCandidato c JOIN FETCH c.experiencias e WHERE e.empresa = :nome AND " +
            "e.empregoAtual = true")
    List<CadastroCandidato> candidatoPorExperienciaAtual(@Param("nome") String nome);

    @Query("SELECT c, e FROM CadastroCandidato c JOIN c.experiencias e JOIN e.profissao p WHERE " +
            "e.empregoAtual = true GROUP BY c")
    List<CadastroCandidato> candidatoTrabalhando();


    List<CadastroCandidato> findNomeAndProfissaoNomeBy();

    @Query("SELECT c FROM CadastroCandidato c JOIN c.profissao p WHERE p.id = :id")
    List<CadastroCandidato> findNomeAndProfissaoByProfissaoId(@Param("id") Integer id);

    @Query("SELECT CONCAT(p.nome, ' - ', COUNT(c)) FROM CadastroCandidato c JOIN c.profissao p GROUP BY p.nome")
    List<String> candidatosPorProfissao();


}