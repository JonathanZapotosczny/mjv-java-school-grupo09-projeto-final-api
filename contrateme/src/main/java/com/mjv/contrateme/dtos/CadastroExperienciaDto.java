package com.mjv.contrateme.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mjv.contrateme.enums.RegimeContratacao;
import com.mjv.contrateme.models.Profissao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CadastroExperienciaDto {

    @NotNull(message = "O campo SALÁRIO é obrigátorio!")
    @DecimalMin(value = "0.0", inclusive = false, message = "O SALÁRIO deve ser maior que zero!")
    private Double salario;

    @NotNull(message = "O campo EMPREGO ATUAL é obrigátorio!")
    private Boolean empregoAtual;

    @NotNull(message = "O campo DATA DE CONTRATAÇÃO é obrigátorio!")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataContratacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataDesligamento;

    @NotNull(message = "O campo EMPRESA é obrigátorio!")
    private Integer empresa;

    @NotNull(message = "O campo REGIME DE CONTRATAÇÃO é obrigátorio!")
    private RegimeContratacao regimeContratacao;

    @NotNull(message = "O campo PROFISSÃO é obrigátorio!")
    private Integer profissao;

    @NotNull(message = "O campo CANDIDATO é obrigátorio!")
    private Integer candidato;
}