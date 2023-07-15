package com.mjv.contrateme.models;

import com.mjv.contrateme.enums.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_candidato")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroCandidato {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(nullable = false, columnDefinition = "DATE")
    private LocalDate dataNascimento;

    @Column(nullable = false, columnDefinition = "VARCHAR(25) DEFAULT 'OUTROS'")
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "candidato_experiencia")
    private List<CadastroExperiencia> experiencias;

    @ManyToMany
    @JoinTable(name = "candidato_habilidade")
    private List<Habilidade> habilidades;

    @Embedded
    @Column(nullable = false)
    private PretensaoSalarial pretensaoSalarial;
}