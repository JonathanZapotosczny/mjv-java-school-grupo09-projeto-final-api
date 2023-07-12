package com.mjv.contrateme.resources;

import com.mjv.contrateme.dtos.HabilidadeDto;
import com.mjv.contrateme.models.Habilidade;
import com.mjv.contrateme.services.HabilidadeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/contrateme/habilidades")
@Tag(name = "Habilidade")
public class HabilidadeResource {

    private final HabilidadeService habilidadeService;

    public HabilidadeResource(HabilidadeService habilidadeService) {
        this.habilidadeService = habilidadeService;
    }

    @PostMapping
    public ResponseEntity<Habilidade> createHabilidade(@RequestBody @Valid HabilidadeDto habilidadeDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.habilidadeService.create(habilidadeDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habilidade> getOneHabilidade(@PathVariable(value = "id") Integer id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.habilidadeService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Page<Habilidade>> getAllHabilidades(Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(this.habilidadeService.findAll(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Habilidade> updateHabilidade(@PathVariable(value = "id") Integer id,
                                                 @RequestBody @Valid HabilidadeDto habilidadeDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.habilidadeService.update(habilidadeDto, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteHabilidade(@PathVariable(value = "id") Integer id) {
        habilidadeService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("HABILIDADE deletada com sucesso!");
    }
}