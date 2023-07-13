package com.mjv.contrateme.resources;

import com.mjv.contrateme.dtos.CadastroCandidatoDto;
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

    @PutMapping("/{id}")
    @Operation(summary = "Realiza a atualização dos dados do candidato selecionado pelo ID")
    public ResponseEntity<CadastroCandidato> updateCandidato(@PathVariable(value = "id") Integer id,
                                                             @RequestBody @Valid CadastroCandidatoDto candidatoDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.update(candidatoDto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui o candidato cadastrado pelo ID")
    public ResponseEntity<Object> deleteCandidato(@PathVariable(value = "id") Integer id) {
        this.candidatoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("CANDIDATO(a) deletado(a) com sucesso!");
    }
}