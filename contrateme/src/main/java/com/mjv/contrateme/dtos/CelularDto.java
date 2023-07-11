package com.mjv.contrateme.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CelularDto {

    @NotNull(message = "O campo NÚMERO é obrigátorio!")
    private Long numero;

    @NotNull(message = "O campo WHATSAPP é obrigátorio!")
    private boolean whatsapp;
}