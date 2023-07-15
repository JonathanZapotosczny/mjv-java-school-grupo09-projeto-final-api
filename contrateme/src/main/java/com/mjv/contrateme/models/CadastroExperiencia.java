package com.mjv.contrateme.models;

import com.mjv.contrateme.enums.RegimeContratacao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_experiencia")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroExperiencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false)
    private Double salario;

    @Column(nullable = false)
    private boolean empregoAtual;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dataContratacao;

    @Column(columnDefinition = "DATE")
    private LocalDate dataDesligamento;

    @Column(nullable = false, length = 100)
    private String empresa;

    @Enumerated(EnumType.STRING)
    private RegimeContratacao regimeContratacao;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Profissao profissao;
}