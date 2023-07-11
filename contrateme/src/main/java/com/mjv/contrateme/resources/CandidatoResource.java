package com.mjv.contrateme.resources;

import com.mjv.contrateme.dtos.CadastroCandidatoDto;
import com.mjv.contrateme.models.CadastroCandidato;
import com.mjv.contrateme.services.CandidatoService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/contratame/candidato")
@Tag(name="Candidato")
public class CandidatoResource {

    private final CandidatoService candidatoService;

    public CandidatoResource(CandidatoService candidatoService) {
        this.candidatoService = candidatoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CadastroCandidato> getOneCandidato(@PathVariable(value="id") Integer id) {

        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.findById(id));

    }
    @PostMapping
    public ResponseEntity<CadastroCandidato> createCandidato(@RequestBody @Valid CadastroCandidatoDto candidatoDto) {
              return ResponseEntity.status(HttpStatus.CREATED).body(this.candidatoService.create(candidatoDto));
    }

    @GetMapping
    public ResponseEntity<Page<CadastroCandidato>> getAllCandidatos(Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoservice.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CadastroCandidato> updateCandidato(@PathVariable(value="id") Integer id,
                                                             @RequestBody @Valid CadastroCandidatoDto candidatoDto) {

        return ResponseEntity.status(HttpStatus.OK).body(this.candidatoService.update(candidatoDto, id));
    }
}