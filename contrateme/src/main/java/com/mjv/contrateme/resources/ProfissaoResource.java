package com.mjv.contrateme.resources;

import com.mjv.contrateme.dtos.EmpresaDto;
import com.mjv.contrateme.dtos.ProfissaoDto;
import com.mjv.contrateme.models.Empresa;
import com.mjv.contrateme.models.Profissao;
import com.mjv.contrateme.services.ProfissaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/contratame/profissao")
@Tag(name="Profissao")
public class ProfissaoResource {

    private final ProfissaoService profissaoService;

    public ProfissaoResource(ProfissaoService profissaoService) {
        this.profissaoService = profissaoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Profissao> getOneProfissao(@PathVariable(value="id") Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(this.profissaoService.findById(id));

    }

    @PostMapping
    public ResponseEntity<Profissao> createProfissao(@RequestBody @Valid ProfissaoDto profissaoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.profissaoService.create(profissaoDto));
    }


}