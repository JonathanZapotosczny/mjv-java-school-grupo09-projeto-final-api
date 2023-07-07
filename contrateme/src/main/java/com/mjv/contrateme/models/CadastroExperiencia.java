package com.mjv.contrateme.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mjv.contrateme.enums.RegimeContratacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "experiencia")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CadastroExperiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Integer id;

    @Column(nullable = false)
    private Double salario;

    @Column(nullable = false)
    private boolean EmpregoAtual;

    @Column(nullable = false)
    private LocalDate dataContratacao;

    private LocalDate dataDesligamento;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Empresa empresa;

    @Enumerated(EnumType.STRING)
    private RegimeContratacao regimeContratacao;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Profissao profissao;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private CadastroCandidato candidato;
}