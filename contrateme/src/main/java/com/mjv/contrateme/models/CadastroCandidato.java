package com.mjv.contrateme.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "candidato")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class CadastroCandidato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 14)
    private String cpf;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Embedded
    @Column(nullable = false)
    private Celular telefoneCelular;

    private Long telefoneFixo;

    @Column(nullable = false, length = 255)
    private String email;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Profissao profissao;

    @Embedded
    @Column(nullable = false)
    private Endereco endereco;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "candidato")
    private List<CadastroExperiencia> experiencias = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "candidato_habilidade")
    private List<Habilidade> habilidades = new ArrayList<>();

    @Embedded
    @Column(nullable = false)
    private PretensaoSalarial pretensaoSalarial;
}