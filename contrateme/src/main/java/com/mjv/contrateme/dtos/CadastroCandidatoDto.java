package com.mjv.contrateme.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CadastroCandidatoDto {

    @NotBlank(message = "O campo NOME é obrigátorio!")
    private String nome;

    @NotBlank(message = "O campo CPF é obrigátorio!")
    @Length(min = 14, max = 14, message = "O campo CPF deverá ter exatamente {max} caracteres!")
    @CPF
    private String cpf;

    @NotNull(message = "O campo DATA DE NASCIMENTO é obrigátorio!")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotNull(message = "Os campos de TELEFONE CELULAR são obrigátorios!")
    private CelularDto telefoneCelular;

    private Long telefoneFixo;

    @NotBlank(message = "O campo E-MAIL é obrigátorio!")
    @Email
    private String email;

    @NotNull(message = "Os campos de PROFISSÃO são obrigátorios!")
    private Integer profissao;

    @NotNull(message = "Os campos de ENDEREÇO são obrigátorios!")
    private EnderecoDto endereco;

    @NotEmpty(message = "Os campos de EXPERIÊNCIAS são obrigátorios!")
    private List<CadastroExperienciaDto> experiencias = new ArrayList<>();

    @NotEmpty(message = "Os campos de HABILIDADES são obrigátorios!")
    private List<Integer> habilidades = new ArrayList<>();

    @NotNull(message = "Os campos de PRETENSÃO SALARIAL são obrigátorios!")
    private PretensaoSalarialDto pretensaoSalarial;
}