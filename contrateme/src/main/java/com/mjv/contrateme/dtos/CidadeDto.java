package com.mjv.contrateme.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CidadeDto {

    @NotBlank(message = "O campo NOME é obrigátorio!")
    private String nome;

    @NotBlank(message = "O campo ESTADO é obrigátorio!")
    private String estado;

    @NotBlank(message = "O campo SIGLA é obrigátorio!")
    @Length(min = 2, max = 2, message = "O campo SIGLA deverá ter exatamente {max} caracteres!")
    private String sigla;
}