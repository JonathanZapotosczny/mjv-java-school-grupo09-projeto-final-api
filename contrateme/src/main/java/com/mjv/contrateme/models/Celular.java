package com.mjv.contrateme.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable

public class Celular {

    @Column(nullable = false)
    private Long numero;

    @Column(nullable = false)
    private boolean whatsapp;
}