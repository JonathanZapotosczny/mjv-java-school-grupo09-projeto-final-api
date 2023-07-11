package com.mjv.contrateme.resources;


import com.mjv.contrateme.dtos.EmpresaDto;
import com.mjv.contrateme.dtos.ProfissaoDto;
import com.mjv.contrateme.models.Empresa;
import com.mjv.contrateme.dtos.CidadeDto;
import com.mjv.contrateme.dtos.ProfissaoDto;
import com.mjv.contrateme.models.Cidade;
import com.mjv.contrateme.models.Profissao;
import com.mjv.contrateme.services.ProfissaoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public ResponseEntity<Page<Profissao>> getAllProfissoes(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(this.profissaoService.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<Profissao> createProfissao(@RequestBody @Valid ProfissaoDto profissaoDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.profissaoService.create(profissaoDto));
   }
  
  @PutMapping("/{id}")
    public ResponseEntity<Profissao> updateProfissao(@PathVariable(value="id") Integer id,
                                               @RequestBody @Valid ProfissaoDto profissaoDto) {

        return ResponseEntity.status(HttpStatus.OK).body(this.profissaoService.update(profissaoDto, id));
  }

}