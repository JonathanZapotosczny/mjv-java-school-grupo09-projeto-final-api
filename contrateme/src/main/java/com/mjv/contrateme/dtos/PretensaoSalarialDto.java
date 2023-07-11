package com.mjv.contrateme.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PretensaoSalarialDto {

    @NotNull(message = "O campo VALOR MÍNIMO é obrigátorio!")
    @DecimalMin(value = "0.0", inclusive = false, message = "O VALOR MÍNIMO deve ser maior que zero!")
    private Double valorMinimo;

    @NotNull(message = "O campo VALOR MÁXIMO é obrigátorio!")
    @DecimalMin(value = "0.0", inclusive = false, message = "O VALOR MÁXIMO deve ser maior que zero!")
    private Double valorMaximo;
}