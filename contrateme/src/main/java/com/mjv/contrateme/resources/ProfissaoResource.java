package com.mjv.contrateme.resources;

import com.mjv.contrateme.dtos.ProfissaoDto;
import com.mjv.contrateme.models.Profissao;
import com.mjv.contrateme.services.ProfissaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/contrateme/profissoes")
@Tag(name = "Profissão")
public class ProfissaoResource {

    private final ProfissaoService profissaoService;

    public ProfissaoResource(ProfissaoService profissaoService) {
        this.profissaoService = profissaoService;
    }

    @PostMapping
    @Operation(summary = "Realiza o cadastro de profissões")
    public ResponseEntity<Profissao> createProfissao(@RequestBody @Valid ProfissaoDto profissaoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.profissaoService.create(profissaoDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna a profissão selecionada pelo ID")
    public ResponseEntity<Profissao> getOneProfissao(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.profissaoService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Retorna a lista de profissões cadastradas")
    public ResponseEntity<Page<Profissao>> getAllProfissoes(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.profissaoService.findAll(pageable));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Realiza a atualização da profissão selecionada pelo ID")
    public ResponseEntity<Profissao> updateProfissao(@PathVariable(value = "id") Integer id,
                                                     @RequestBody @Valid ProfissaoDto profissaoDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.profissaoService.update(profissaoDto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui a profissão pelo ID")
    public ResponseEntity<Object> deleteProfissao(@PathVariable(value = "id") Integer id) {
        profissaoService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("PROFISSÃO deletada com sucesso!");
    }
}