package com.mjv.contrateme.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Endereco {

    @Column(nullable = false)
    private Long cep;

    @Column(nullable = false, length = 255)
    private String logradouro;

    @Column(nullable = false, length = 6)
    private String numeroEndereco;

    @Column(length = 255)
    private String complemento;

    @Column(nullable = false, length = 60)
    private String bairro;

    @ManyToOne(cascade = CascadeType.ALL)
    private Cidade cidade;
}