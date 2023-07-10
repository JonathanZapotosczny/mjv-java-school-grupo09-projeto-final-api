package com.mjv.contrateme.resources;

import com.mjv.contrateme.models.Profissao;
import com.mjv.contrateme.services.ProfissaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}