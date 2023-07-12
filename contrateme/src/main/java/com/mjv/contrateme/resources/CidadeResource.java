package com.mjv.contrateme.resources;

import com.mjv.contrateme.dtos.CidadeDto;
import com.mjv.contrateme.models.Cidade;
import com.mjv.contrateme.services.CidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/contrateme/cidades")
@Tag(name = "Cidade")
public class CidadeResource {

    private final CidadeService cidadeService;

    public CidadeResource(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @PostMapping
    public ResponseEntity<Cidade> createCidade(@RequestBody @Valid CidadeDto cidadeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.cidadeService.create(cidadeDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> getOneCidade(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cidadeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Cidade>> getAllCidades(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cidadeService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> updateCidade(@PathVariable(value = "id") Integer id,
                                               @RequestBody @Valid CidadeDto cidadeDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.cidadeService.update(cidadeDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCidade(@PathVariable(value = "id") Integer id) {
        this.cidadeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("CIDADE deletada com sucesso!");
    }
}