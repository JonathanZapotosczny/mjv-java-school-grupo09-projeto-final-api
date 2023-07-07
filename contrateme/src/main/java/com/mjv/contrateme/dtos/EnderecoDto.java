package com.mjv.contrateme.dtos;

import com.mjv.contrateme.models.Cidade;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class EnderecoDto {

    @NotNull(message = "O campo CEP é obrigátorio!")
    private Long cep;

    @NotBlank(message = "O campo LOGRADOURO é obrigátorio!")
    private String logradouro;

    @NotBlank(message = "O campo NÚMERO DO ENDEREÇO é obrigátorio!")
    private String numeroEndereco;

    private String complemento;

    @NotBlank(message = "O campo BAIRRO é obrigátorio!")
    private String bairro;

    @NotNull(message = "O campo CIDADE é obrigátorio!")
    private int cidade;
}