package com.mjv.contrateme.resources;

import com.mjv.contrateme.dtos.CidadeDto;
import com.mjv.contrateme.dtos.EmpresaDto;
import com.mjv.contrateme.models.Cidade;
import com.mjv.contrateme.models.Empresa;
import com.mjv.contrateme.services.EmpresaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/contratame/empresa")
@Tag(name="Empresa")
public class EmpresaResource {

    private final EmpresaService empresaService;

    public EmpresaResource(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Empresa> getOneEmpresa(@PathVariable(value="id") Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(this.empresaService.findById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<Empresa> updateEmpresa(@PathVariable(value="id") Integer id,
                                               @RequestBody @Valid EmpresaDto empresaDto) {

        return ResponseEntity.status(HttpStatus.OK).body(this.empresaService.update(empresaDto, id));
    }

}