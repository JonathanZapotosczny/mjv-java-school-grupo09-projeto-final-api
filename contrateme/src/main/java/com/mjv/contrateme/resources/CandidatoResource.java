package com.mjv.contrateme.resources;

import com.mjv.contrateme.dtos.CadastroCandidatoDto;
import com.mjv.contrateme.dtos.CadastroCandidatoDtoResponse;
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
import java.util.Date;
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
    public ResponseEntity<Integer> contarCandidatoComHabilidade(@RequestParam(value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.contarCandidatoComHabilidade(nome));
    }

    @GetMapping("/sem-habilidade")
    public ResponseEntity<List<CadastroCandidato>> buscarCandidatoComHabilidade(@RequestParam(value = "nome",
            required = false, defaultValue = "") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.buscarCandidatoSemHabilidade(nome));
    }

    @GetMapping("/por-cidade")
    public ResponseEntity<List<String>> quantidadeProfissionaisPorCidade(@RequestParam(value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.quantidadeProfissionaisPorCidade(nome));
    }

    @GetMapping("/experiencia-por-periodo")
    public ResponseEntity<List<CadastroCandidato>> candidatosComExperienciaPorData(
            @RequestParam(value = "dataInicio") LocalDate dataInicio, @RequestParam(value = "dataFim") LocalDate dataFim) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatosComExperienciaPorData
                (dataInicio, dataFim));
    }

    @GetMapping("/experiencia-por-empresa")
    public ResponseEntity<List<CadastroCandidato>> candidatoPorExperiencia(@RequestParam(value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatoPorExperiencia(nome));
    }

    @GetMapping("/experiencia-por-empresa-atual")
    public ResponseEntity<List<CadastroCandidato>> candidatoPorExperienciaAtual(@RequestParam(value = "nome") String nome) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatoPorExperienciaAtual(nome));
    }

    @GetMapping("/trabalhando-atualmente")
    public ResponseEntity<List<CadastroCandidato>> candidatoTrabalhando() {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatoTrabalhando());
    }

    @GetMapping("/profissao")
    public ResponseEntity<List<CadastroCandidatoDtoResponse>> profissaoDoCandidato() {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.profissaoDoCandidato());
    }

    @GetMapping("/profissao-por-id")
    public ResponseEntity<List<CadastroCandidatoDtoResponse>> profissaoPorId(@RequestParam(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.profissaoPorId(id));
    }

    @GetMapping("/quantidade-profissionais")
    public ResponseEntity<List<String>> candidatosPorProfissao() {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.candidatosPorProfissao());
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