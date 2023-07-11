package com.mjv.contrateme.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HabilidadeDto {

    @NotBlank(message = "O campo HABILIDADE é obrigátorio!")
    private String habilidade;

    @NotEmpty(message = "O campo CANDIDATO é obrigátorio!")
    private int candidato;
}