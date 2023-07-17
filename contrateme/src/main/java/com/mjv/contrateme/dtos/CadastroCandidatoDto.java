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

    @NotBlank(message = "O campo NOME é obrigatório!")
    private String nome;

    @NotBlank(message = "O campo CPF é obrigatório!")
    @Length(min = 14, max = 14, message = "O campo CPF deverá ter exatamente {max} caracteres!")
    @CPF
    private String cpf;

    @NotNull(message = "O campo DATA DE NASCIMENTO é obrigatório!")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    private String sexo;

    @NotNull(message = "Os campos de TELEFONE CELULAR são obrigatórios!")
    private CelularDto telefoneCelular;

    private Long telefoneFixo;

    @NotBlank(message = "O campo E-MAIL é obrigatório!")
    @Email
    private String email;

    @NotNull(message = "Os campos de PROFISSÃO são obrigatórios!")
    private Integer profissao;

    @NotNull(message = "Os campos de ENDEREÇO são obrigatórios!")
    private EnderecoDto endereco;

    @NotEmpty(message = "Os campos de EXPERIÊNCIAS são obrigatórios!")
    private List<CadastroExperienciaDto> experiencias = new ArrayList<>();

    private List<Integer> habilidades = new ArrayList<>();

    @NotNull(message = "Os campos de PRETENSÃO SALARIAL são obrigatórios!")
    private PretensaoSalarialDto pretensaoSalarial;
}