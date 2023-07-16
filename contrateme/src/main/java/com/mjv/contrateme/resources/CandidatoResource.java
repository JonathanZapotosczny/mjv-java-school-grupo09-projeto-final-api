package com.mjv.contrateme.resources;

import com.mjv.contrateme.dtos.CadastroCandidatoDto;
import com.mjv.contrateme.dtos.CadastroCandidatoDtoResponse;
import com.mjv.contrateme.enums.Sexo;
import com.mjv.contrateme.models.CadastroCandidato;
import com.mjv.contrateme.services.CandidatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/contrateme/candidatos")
@Tag(name = "Candidato")
public class CandidatoResource {

    private final CandidatoService candidatoService;

    public CandidatoResource(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @PostMapping
    @Operation(summary = "Realiza o cadastro de candidatos")
    public ResponseEntity<CadastroCandidato> createCandidato(@RequestBody @Valid CadastroCandidatoDto candidatoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.candidatoService.create(candidatoDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna o candidato selecionado pelo ID")
    public ResponseEntity<CadastroCandidato> getOneCandidato(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Retorna a lista de candidatos cadastrados")
    public ResponseEntity<Page<CadastroCandidato>> getAllCandidatos(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.findAll(pageable));
    }

    @GetMapping("/habilidade")
    @Operation(summary = "Retorna a quantidade de candidatos  que possuem a habilidade procurada")
    public ResponseEntity<Integer> contarCandidatosComHabilidade(@RequestParam(value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.contarCandidatoComHabilidade(nome));
    }

    @GetMapping("/sem-habilidade")
    @Operation(summary = "Retorna a lista de candidatos que não possuem habilidade relacionada")
    public ResponseEntity<List<CadastroCandidato>> buscarCandidatosComHabilidade(@RequestParam(value = "nome",
            required = false, defaultValue = "") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.buscarCandidatosSemHabilidade(nome));
    }

    @Operation(summary = "Retorna a lista de candidatos filtrados por sexo e cidade")
    @GetMapping("/sexo-e-endereco")
    public ResponseEntity<List<CadastroCandidato>> candidatosPorSexoEEndereco(@RequestParam(value = "sexo",
            required = true) Sexo sexo, @RequestParam(value = "sigla",
            required = false, defaultValue = "") String sigla) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatosPorSexoEEndereco(sexo, sigla));
    }

    @Operation(summary = "Retorna a lista de candidatos que moram na cidade pesquisada.")
    @GetMapping("/por-cidade")
    public ResponseEntity<List<String>> quantidadeProfissionaisPorCidade(@RequestParam(value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.quantidadeProfissionaisPorCidade(nome));
    }

    @Operation(summary = "Retorna a lista de candidatos com registro de experiência entre um período")
    @GetMapping("/experiencia-por-periodo")
    public ResponseEntity<List<CadastroCandidato>> candidatosComExperienciaPorData(
            @RequestParam(value = "dataInicio") LocalDate dataInicio, @RequestParam(value = "dataFim") LocalDate dataFim) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatosComExperienciaPorData
                (dataInicio, dataFim));
    }

    @Operation(summary = "Retorna a lista de candidatos que trabalharam na empresa pesquisada")
    @GetMapping("/experiencia-por-empresa")
    public ResponseEntity<List<CadastroCandidato>> candidatosPorExperiencia(@RequestParam(value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatosPorExperiencia(nome));
    }

    @Operation(summary = "Retorna a lista de candidatos que ainda trabalham na empresa pesquisada")
    @GetMapping("/experiencia-por-empresa-atual")
    public ResponseEntity<List<CadastroCandidato>> candidatosPorExperienciaAtual(@RequestParam(value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatosPorExperienciaAtual(nome));
    }

    @Operation(summary = "Retorna a lista de candidatos que estão trabalhando no momento")
    @GetMapping("/trabalhando-atualmente")
    public ResponseEntity<List<CadastroCandidato>> candidatosTrabalhando() {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatosTrabalhando());
    }

    @Operation(summary = "Retorna a lista de candidatos e nome da sua profissão correspondentemente")
    @GetMapping("/profissao")
    public ResponseEntity<List<CadastroCandidatoDtoResponse>> profissaoesDosCandidatos() {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.profissoesDosCandidatos());
    }

    @Operation(summary = "Retorna a lista de candidatos e nome da sua profissão por ID")
    @GetMapping("/profissao-por-id")
    public ResponseEntity<List<CadastroCandidatoDtoResponse>> profissoesPorId(@RequestParam(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.profissoesPorId(id));
    }

    @Operation(summary = "Retorna uma lista com a quantidade de profissionais por profissão")
    @GetMapping("/quantidade-profissionais")
    public ResponseEntity<List<String>> candidatosPorProfissao() {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatosPorProfissao());
    }

    @Operation(summary = "Retorna uma lista de candidatos ordenados por profissão e salário máximo de forma decrescente")
    @GetMapping("/profissao-e-salario")
    public ResponseEntity<List<CadastroCandidato>> candidatosPorProfissaoESalario(@RequestParam(value = "nome",
            required = false, defaultValue = "") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatosPorProfisaoESalario(nome));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Realiza a atualização dos dados do candidato selecionado pelo ID")
    public ResponseEntity<CadastroCandidato> updateCandidato(@PathVariable(value = "id") Integer id,
                                                             @RequestBody @Valid CadastroCandidatoDto candidatoDto) {
        System.out.println(candidatoDto.toString());
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.update(candidatoDto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui o candidato cadastrado pelo ID")
    public ResponseEntity<Object> deleteCandidato(@PathVariable(value = "id") Integer id) {
        this.candidatoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("CANDIDATO(a) deletado(a) com sucesso!");
    }
}