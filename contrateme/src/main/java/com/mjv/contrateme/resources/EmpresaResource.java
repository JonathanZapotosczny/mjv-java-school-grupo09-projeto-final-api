package com.mjv.contrateme.resources;

import com.mjv.contrateme.dtos.EmpresaDto;
import com.mjv.contrateme.models.Empresa;
import com.mjv.contrateme.services.EmpresaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/contrateme/empresas")
@Tag(name = "Empresa")
public class EmpresaResource {

    private final EmpresaService empresaService;

    public EmpresaResource(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    @Operation(summary = "Realiza o cadastro de empresas")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody @Valid EmpresaDto empresaDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.empresaService.create(empresaDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Retorna a empresa selecionada pelo ID")
    public ResponseEntity<Empresa> getOneEmpresa(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.empresaService.findById(id));
    }

    @GetMapping
    @Operation(summary = "Retorna a lista de empresas cadastradas")
    public ResponseEntity<Page<Empresa>> getAllEmpresas(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.empresaService.findAll(pageable));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Realiza a atualização da empresa selecionada pelo ID")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable(value = "id") Integer id,
                                                 @RequestBody @Valid EmpresaDto empresaDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.empresaService.update(empresaDto, id));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Exclui a empresa pelo ID")
    public ResponseEntity<Object> deleteEmpresa(@PathVariable(value = "id") Integer id) {
        empresaService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("EMPRESA deletada com sucesso!");
    }
}