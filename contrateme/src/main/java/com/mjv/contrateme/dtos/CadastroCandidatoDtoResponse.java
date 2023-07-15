package com.mjv.contrateme.dtos;

import com.mjv.contrateme.models.CadastroCandidato;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroCandidatoDtoResponse {

    private String nome;

    private String profissao;

    public CadastroCandidatoDtoResponse(CadastroCandidato candidato) {
        nome = candidato.getNome();
        profissao = candidato.getProfissao().getNome();
    }
}